package edu.eckerd.moodleclient.core.enrol

import edu.eckerd.moodleclient.MoodleAble
import io.circe.Json
import org.http4s.UrlForm
import cats.implicits._
import edu.eckerd.moodleclient.models.User

case class GetEnrolledUsers(courseid: Int)

object GetEnrolledUsers {
  implicit val getEnrolledUsersMoodleable = new MoodleAble[GetEnrolledUsers, List[User]] {
    override def render(input: GetEnrolledUsers): UrlForm = UrlForm(
      ("wsfunction","core_enrol_get_enrolled_users"),
      ("courseid", input.courseid.show)
    )
  }

}