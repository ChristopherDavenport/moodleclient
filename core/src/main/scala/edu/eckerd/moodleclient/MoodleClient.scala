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

  def fetchAs[I, O](moodleFunction: MoodleFunction[I, O]) // I => UrlForm)
                  (implicit decoder: Decoder[O]): Kleisli[Task, I, O] =  Kleisli[Task, I, O]( (input: I) => {

    val form = moodleFunction(input).+(("moodlewsrestformat", "json"))
    val request = POST(server.webserviceUrl, form)
      client.expect(request)(jsonOf[O])
    }
  )

}
