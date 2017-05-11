package edu.eckerd.moodleclient.models

import io.circe.generic.JsonCodec

@JsonCodec case class Warning(
                  item: String,
                  warningcode: String,
                  message: String
                  )
