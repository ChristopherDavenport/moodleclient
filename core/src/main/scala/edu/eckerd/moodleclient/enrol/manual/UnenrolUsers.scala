package edu.eckerd.moodleclient.enrol.manual

import edu.eckerd.moodleclient.MoodleAble
import io.circe.Json
import org.http4s.UrlForm

import scala.annotation.tailrec

case class DeleteEnrolment(enrolments:List[(Int, Int)])

object DeleteEnrolment{

  implicit val deleteEnrolmentMoodleable = new MoodleAble[DeleteEnrolment, Unit] {

    def render(input: DeleteEnrolment): UrlForm = {
      val functionName = List(("wsfunction", "enrol_manual_unenrol_users"))
      val refined = idToTuples(input.enrolments)
      UrlForm(
        functionName ++ refined:_*
      )
    }
  }

  def createFormTuple(userId: Int, courseId: Int, index: Int): List[(String, String)] = List(
    (s"enrolments[$index][userid]",  userId.toString),
    (s"enrolments[$index][courseid]", courseId.toString)
  )

  def idToTuples(l:List[(Int, Int)]): List[(String, String)] = {
    listIndexes(l)
      .flatMap{ case (element, index) => createFormTuple(element._1, element._2,index) }
  }

  def listIndexes[A](input: List[A]): List[(A, Int)] = {

    @tailrec
    def go (input: List[A], acc: List[(A, Int)] ): List[(A, Int)] = {
      input match {
        case Nil => acc.reverse
        case x :: xs => go (xs, (x, acc.length) :: acc)
      }

    }
    go (input, List.empty[(A, Int)] )
}


}