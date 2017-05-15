package edu.eckerd.moodleclient.models

import io.circe.generic.JsonCodec

@JsonCodec case class Role(
                            roleid: Int,
                            name: String,
                            shortname: String,
                            sortOrder: Option[Int]
                          )
