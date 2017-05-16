package edu.eckerd.moodleclient.models

import io.circe.generic.JsonCodec

@JsonCodec case class CreatedCategory(
                                     id : Int,
                                     name: String
                                     )
