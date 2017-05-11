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
                        server: MoodleServer,
                        token: Option[Token]
                       ) {

  def fetch[I, O](moodleFunction: I)
                 (implicit moodleable: MoodleAble[I, O], decoder : Decoder[O]): Task[O] =  {

    val form = moodleable.render(moodleFunction).+(("moodlewsrestformat", "json"))
    val authform = token.map(token => form.+(("wstoken", token))).getOrElse(form)


    val request = POST(server.webserviceUrl, authform)
      client.expect(request)(jsonOf[O])
  }


}
