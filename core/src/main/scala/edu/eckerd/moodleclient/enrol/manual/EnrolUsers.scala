package edu.eckerd.moodleclient.enrol.manual

import edu.eckerd.moodleclient.MoodleAble
import org.http4s.UrlForm
import cats.implicits._

case class EnrolUsers(enrolments: List[EnrolUser])


object EnrolUsers {

  implicit val enrolUsersMoodleAble = new MoodleAble[EnrolUsers, Unit] {
    override def render(input: EnrolUsers): UrlForm = renderUsers(input.enrolments)
  }

  def renderUsers(l: List[EnrolUser]): UrlForm = {
    val base : List[(String, String)] = l.zipWithIndex.flatMap{ case (user, index) => renderUser(user, index)}
    UrlForm(
      ("wsfunction", "enrol_manual_enrol_users") :: base :_*
    )
  }

  def renderUser(user: EnrolUser, index: Int): List[(String, String)] = {
    val timestart = user.timestart
      .map(ts => List((s"enrolments[$index][timestart]", ts.show)))
      .getOrElse(List.empty[(String, String)])
    val timend = user.timeend
      .map(te => List((s"enrolments[$index][timeend]", te.show)))
      .getOrElse(List.empty[(String, String)])
    val suspend = user.suspend
      .map(b => List((s"enrolments[$index][timeend]", if (b) "1" else "0")))
      .getOrElse(List.empty[(String, String)])


    val base = List(
      (s"enrolments[$index][roleid]", user.roleid.value.show),
      (s"enrolments[$index][userid]", user.userid.show),
      (s"enrolments[$index][courseid]", user.courseid.show)
    )

    base ::: timestart ::: timend ::: suspend
  }


}


