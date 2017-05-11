package edu.eckerd.moodleclient.core.user

import edu.eckerd.moodleclient.MoodleAble
import edu.eckerd.moodleclient.models.Users
import io.circe.Json
import org.http4s.UrlForm

case class GetUsers(
                   key: Option[String] = None,
                   value: Option[String] = None
                   )

object GetUsers {

  implicit val GetUsersMoodleAble = new MoodleAble[GetUsers, Users]{
    def render(input: GetUsers): UrlForm = {
      val functionName = List(("wsfunction", "core_user_get_users"))
      val refined = List(
        ("criteria[0][key]", input.key.getOrElse("")),
        ("criteria[0][value]", input.value.getOrElse(""))
      )

      UrlForm(
        functionName ++ refined:_*
      )
    }
  }

}
