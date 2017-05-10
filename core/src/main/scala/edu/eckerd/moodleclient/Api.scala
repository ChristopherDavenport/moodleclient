package edu.eckerd.moodleclient

import fs2.Task
import io.circe.Json
import org.http4s.client.Client
import org.http4s.client._
import org.http4s.{EntityDecoder, Method, Uri, UrlForm}
import org.http4s.dsl._

trait AuthorizedApi[O] {

  val path : Uri.Path
  val form : UrlForm

  val authString: Token => Uri.Path =  token =>
    "webservice/rest/server.php" +
      "?" + "wstoken=" + token +
      "&" + "moodlewsrestformat=json"

  private def serviceCall = "&" + "wsfunction=" + path


  def get(client: Client, hostname: Uri, extension: String, token: String)(implicit entityDecoder: EntityDecoder[O]): Task[O] = {
    Task
      .now(hostname.withPath( "/" + extension + "/" + authString(token) + serviceCall))
      .flatMap{v => Task.delay{println(v); v}}
      .flatMap { uri =>client.expect[O](POST(uri,  form))}
  }

  def login[B](client: Client, hostname: Uri, extension: String, username: String, password: String)
           (implicit entityDecoder: EntityDecoder[B]): Task[B] = {
    Task
      .delay(hostname.withPath("/" + extension + "/" + "login/token.php"))
      .flatMap{ uri =>
        val form = UrlForm(("service", "moodle_mobile_app"), ("username", username), ("password", password))
          client.expect[B]{POST(uri, form)}
      }

  }

}

case class Core_User_View_User_List(courseid: String) extends AuthorizedApi[Json] {
  val path = "core_user_view_user_list"
  val form = UrlForm(("courseid", courseid))
}

case object Core_Course_Get_Course extends AuthorizedApi[Json] {
  val path = "core_course_get_courses"
  val form = UrlForm()
}
case object Core_User_Get_Users extends AuthorizedApi[Json] {
  val path = "core_user_get_users"
  val form = UrlForm()
}

case class Core_Enrol_Get_Enrolled_Users(courseid: String) extends AuthorizedApi[Json] {
  val path = "core_enrol_get_enrolled_users"
  val form = UrlForm(("courseid", courseid))
}

