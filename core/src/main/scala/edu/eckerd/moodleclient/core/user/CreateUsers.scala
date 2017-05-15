package edu.eckerd.moodleclient.core.user

import edu.eckerd.moodleclient.MoodleAble
import edu.eckerd.moodleclient.models.{CreatedUser, User, Users}
import io.circe.Json
import org.http4s.UrlForm



case class CreateUsers(users: List[CreateUser])

object CreateUsers {
  implicit val CreateUsersMoodleAble = new MoodleAble[CreateUsers, List[CreatedUser]]{
    def render(input: CreateUsers): UrlForm = {
      renderUsers(input.users)
    }
  }

  def renderUsers(l : List[CreateUser]): UrlForm = {
    val functionName = List(("wsfunction", "core_user_create_users"))
    val refined = l.zipWithIndex.flatMap{ case(user, index) => renderUser(user, index)}

    UrlForm(
      functionName ++ refined:_*
    )
  }

  def renderUser(cUser: CreateUser, index: Int): List[(String, String)] = {
    val user = cUser.user
    val boolInt: Boolean => Int = bool => if (bool) 1 else 0

    val lang : List[(String, String)] = user.lang.map(l => List((s"users[$index][lang]", l))).getOrElse(List.empty[(String, String)]) //= string
    val calendarType : List[(String, String)] = user.calendartype.map(ct => List((s"users[$index][calendartype]",ct))).getOrElse(List.empty[(String, String)])
    val city : List[(String, String)] = user.city.map(c => List((s"users[$index][city]", c))).getOrElse(List.empty[(String, String)])
    val country : List[(String, String)] = user.country.map(c => List((s"users[$index][country]", c))).getOrElse(List.empty[(String, String)])
    val password: List[(String, String)] = cUser.password.map(p => List((s"users[$index][password]", p))).getOrElse(List.empty[(String, String)])
    val auth : List[(String, String)] = user.auth.map(auth => List((s"users[$index][auth]", auth))).getOrElse(List.empty[(String, String)])

    val base: List[(String, String)] = List(
      (s"users[$index][username]", user.username),
      (s"users[$index][firstname]", user.firstname), //= string
      (s"users[$index][lastname]", user.lastname), //= string
      (s"users[$index][email]", user.email), //= string
      (s"users[$index][idnumber]", user.idnumber.getOrElse("")), //= string
      (s"users[$index][createpassword]", boolInt(cUser.createPassword).toString) //= int
    )

    base ::: auth ::: lang ::: calendarType ::: city ::: country ::: password


  }




}