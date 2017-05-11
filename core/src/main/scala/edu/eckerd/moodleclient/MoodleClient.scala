package edu.eckerd.moodleclient

import cats.data.Kleisli
import fs2.Task
import org.http4s.circe._
import io.circe._
import org.http4s.client.Client
import org.http4s.client._
import org.http4s.EntityDecoder
import org.http4s.dsl._

case class MoodleClient(
                        client: Client,
                        server: MoodleServer
                       ) {

  def fetch[I, O](moodleFunction: I)
                 (implicit moodleable: MoodleAble[I, O], decoder : Decoder[O]): Task[O] =  {

    val form = moodleable.render(moodleFunction).+(("moodlewsrestformat", "json"))
    val request = POST(server.webserviceUrl, form)
      client.expect(request)(jsonOf[O])
  }


}
