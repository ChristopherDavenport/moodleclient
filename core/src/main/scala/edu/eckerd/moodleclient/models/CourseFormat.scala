package edu.eckerd.moodleclient.models
import io.circe.generic.JsonCodec

@JsonCodec case class CourseFormat(
                       name: String,
                       value: Int
                       )
