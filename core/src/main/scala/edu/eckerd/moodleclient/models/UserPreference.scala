package edu.eckerd.moodleclient.models

import io.circe.{Decoder, Encoder, HCursor, Json}

case class UserPreference(
                         name: String,
                         value: String
                         )

object UserPreference{
  implicit val decodeUserPreference = new Decoder[UserPreference] {
    private val stringDecoder : Decoder[String] =
        Decoder[String].or(Decoder[Long].map(_.toString))

    final def apply(c: HCursor): Decoder.Result[UserPreference] =
      for {
        name <- c.downField("name").as[String]
        value <- c.downField("value").as[String](stringDecoder)
      } yield {
        UserPreference(name, value)
      }
  }

  implicit val encodeUserPreference = new Encoder[UserPreference]{
    final def apply(a: UserPreference): Json = Json.obj(
      ("name", Json.fromString(a.name)),
      ("value", Json.fromString(a.value))
    )
  }



}
