package edu.eckerd.moodleclient.models

import io.circe.generic.JsonCodec

@JsonCodec case class CreatedUser(id: Int, username: String)
