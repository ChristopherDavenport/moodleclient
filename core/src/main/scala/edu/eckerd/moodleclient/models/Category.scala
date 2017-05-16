package edu.eckerd.moodleclient.models

import io.circe.generic.JsonCodec

@JsonCodec case class Category(
                                name: String,
                                id: Option[Int] = None,
                                idnumber: Option[String] = None,
                                description: Option[String] = None,
                                descriptionformat : Option[Int] = None,
                                parent: Option[Int] = None,
                                sortorder: Option[Int] = None,
                                coursecount: Option[Int] = None,
                                visible: Option[Int] = None,
                                visibleold: Option[Int] = None,
                                timemodified: Option[Int] = None,
                                depth: Option[Int] = None,
                                path: Option[String] = None,
                                theme: Option[String] = None
                              )
