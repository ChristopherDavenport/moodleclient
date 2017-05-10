package edu.eckerd

import cats.implicits._
import cats.data.Kleisli
import fs2.Task
import org.http4s.UrlForm

package object moodleclient {

  type Token = String

  type MoodleFunction[I, O] = Function[I,UrlForm]

}
