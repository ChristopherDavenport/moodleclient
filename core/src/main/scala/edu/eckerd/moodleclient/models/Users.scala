package edu.eckerd.moodleclient.models

import io.circe.generic.JsonCodec

@JsonCodec case class Users(
                users: List[User],
                warnings: List[Warning]
                )
