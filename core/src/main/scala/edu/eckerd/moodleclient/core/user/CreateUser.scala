package edu.eckerd.moodleclient.core.user

import cats.data.NonEmptyList
import edu.eckerd.moodleclient.MoodleAble
import edu.eckerd.moodleclient.models.{CreatedUser, User}
import org.http4s.UrlForm

case class CreateUser(user: User, password: Option[String] = Some("Default#123"), createPassword: Boolean = false)

object CreateUser {

  implicit val CreateUserMoodleAble = new MoodleAble[CreateUser, List[CreatedUser]]{
    override def render(input: CreateUser): UrlForm =
      CreateUsers.renderUsers(NonEmptyList.of(input))
  }

}