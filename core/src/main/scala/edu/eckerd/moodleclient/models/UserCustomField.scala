package edu.eckerd.moodleclient.models

import io.circe.generic.JsonCodec

@JsonCodec case class UserCustomField(
                          `type`: String,
                          value: String,
                          name: String,
                          shortname: String
                          )
