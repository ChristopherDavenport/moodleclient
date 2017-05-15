package edu.eckerd.moodleclient.core.user

import edu.eckerd.moodleclient.models.User

case class CreateUser(user: User, password: Option[String] = Some("Default#123"), createPassword: Boolean = false)

