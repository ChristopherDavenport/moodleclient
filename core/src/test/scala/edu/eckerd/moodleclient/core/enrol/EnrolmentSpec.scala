package edu.eckerd.moodleclient.core.enrolment

import edu.eckerd.moodleclient.models.CourseRole
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.{FlatSpec, Matchers}
import io.circe._
import io.circe.syntax._
import io.circe.literal._
import edu.eckerd.moodleclient.models._


class EnrolmentSpec extends FlatSpec with Matchers {

  "GetEnrolledUsers" should "parse from Valid Json" in {
    val validJson =
      json"""[
  {
    "id" : 4,
    "username" : "bjones",
    "firstname" : "Batman",
    "lastname" : "Jones",
    "fullname" : "Batman Jones",
    "email" : "batman@batman.com",
    "department" : "",
    "firstaccess" : 1494448988,
    "lastaccess" : 1495031280,
    "profileimageurlsmall" : "https://aristophanes.eckerd.edu/moodle_kevin/theme/image.php/boost/core/1495032290/u/f2",
    "profileimageurl" : "https://aristophanes.eckerd.edu/moodle_kevin/theme/image.php/boost/core/1495032290/u/f1",
    "groups" : [
    ],
    "roles" : [
      {
        "roleid" : 3,
        "name" : "",
        "shortname" : "editingteacher",
        "sortorder" : 0
      }
    ],
    "enrolledcourses" : [
      {
        "id" : 2,
        "fullname" : "Batman Jones",
        "shortname" : "batman"
      }
    ]
  }
]"""
    validJson.as[List[User]] shouldBe a [Right[_,_]]
  }
}
