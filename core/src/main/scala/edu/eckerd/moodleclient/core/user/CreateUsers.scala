package edu.eckerd.moodleclient.core.user

import edu.eckerd.moodleclient.MoodleAble
import edu.eckerd.moodleclient.models.{CreatedUser, User, Users}
import io.circe.Json
import org.http4s.UrlForm

case class CreateUsers(users: List[User])

object CreateUsers {
  implicit val CreateUsersMoodleAble = new MoodleAble[CreateUsers, List[CreatedUser]]{

    def render(input: CreateUsers): UrlForm = {
      val functionName = List(("wsfunction", "core_user_create_users"))
      val head = input.users.head
      val refined = List(
        ("users[0][username]", head.username), //", ""
      ("users[0][password]", "Password123#"),  //= string
//      ("users[0][createpassword]", "0"), //= int
      ("users[0][firstname]", head.firstname), //= string
      ("users[0][lastname]", head.lastname), //= string
      ("users[0][email]", head.email), //= string
      ("users[0][auth]", head.auth), //= string
      ("users[0][idnumber]", head.idnumber.getOrElse("")) //= string
//      ("users[0][lang]", head.lang.getOrElse("en")), //= string
//      ("users[0][calendartype]", head.calendartype.getOrElse("gregorian")), //= string
//      ("users[0][theme]", ""), //= string
//      ("users[0][timezone]", ""), //= string
//      ("users[0][mailformat]", ""), //= int
//      ("users[0][description]", ""), //= string
//      ("users[0][city]", head.city.getOrElse("St Petersburg")), //= string
//      ("users[0][country]", head.country.getOrElse("US")) //= string
//      ("users[0][firstnamephonetic]", ""), //= string
//      ("users[0][lastnamephonetic]", ""), //= string
//      ("users[0][middlename]", ""), //= string
//      ("users[0][alternatename]", ""), //= string
//      ("users[0][preferences][0][type]", ""), //= string
//      ("users[0][preferences][0][value]", ""), //= string
//      ("users[0][customfields][0][type]", ""), //= string
//      ("users[0][customfields][0][value]", "") //= string
      )

      UrlForm(
        (functionName ++ refined):_*
//        functionName ++ refined:_*
      )
    }
  }


}