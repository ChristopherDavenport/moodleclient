package edu.eckerd.moodleclient.core.user

import edu.eckerd.moodleclient.MoodleAble
import io.circe.Json
import org.http4s.UrlForm

import scala.annotation.tailrec


case class DeleteUsers(ids:List[Int])

object DeleteUsers{

  implicit val deleteUsersMoodleable = new MoodleAble[DeleteUsers, Json] {
    def render(input: DeleteUsers): UrlForm = {
      val functionName = List(("wsfunction", "core_user_delete_users"))
      val refined = idToTuples(input.ids)

      UrlForm(
        functionName ++ refined:_*
      )
    }
  }


  def createFormTuple(id: Int, index: Int): (String, String) = {
    (s"userids[$index]",  id.toString)
  }

  def idToTuples(l:List[Int]): List[(String, String)] = {
    listIndexes(l)
      .map{ case (element, index) => createFormTuple(element,index) }
  }

  def listIndexes[A](input: List[A]): List[(A, Int)] = {

    @tailrec
    def go(input: List[A], acc: List[(A, Int)]): List[(A, Int)] = {
      input match {
        case Nil => acc.reverse
        case x :: xs => go(xs, (x, acc.length) :: acc)
      }

    }
    go(input, List.empty[(A, Int)])
  }

}