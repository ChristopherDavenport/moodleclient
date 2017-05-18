package edu.eckerd.moodleclient.models

import io.circe.generic.JsonCodec

@JsonCodec case class CreatedCourse(
                                     id: Int,
                                     shortname: String
                                   )