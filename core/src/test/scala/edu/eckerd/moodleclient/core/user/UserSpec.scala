package edu.eckerd.moodleclient.core.user

import org.scalatest.{FlatSpec, Matchers}
import io.circe._
import io.circe.syntax._
import io.circe.literal._
import edu.eckerd.moodleclient.models._

class UserSpec extends FlatSpec with Matchers {

  "User" should "parse from Valid Json" in {
    val validJson =json"""
            {
              "id" : 2,
              "username" : "moodleadmin",
              "firstname" : "Moodle",
              "lastname" : "Admin",
              "fullname" : "Moodle Admin",
              "email" : "moodle@eckerd.edu",
              "department" : "",
              "firstaccess" : 1494445508,
              "lastaccess" : 1494518647,
              "auth" : "manual",
              "suspended" : false,
              "confirmed" : true,
              "lang" : "en",
              "theme" : "",
              "timezone" : "99",
              "mailformat" : 1,
              "description" : "",
              "descriptionformat" : 1,
              "city" : "St Petersburg",
              "country" : "US",
              "profileimageurlsmall" : "https://moodletest.eckerd.edu/moodle_kevin/theme/image.php/boost/core/1494448918/u/f2",
              "profileimageurl" : "https://moodletest.eckerd.edu/moodle_kevin/theme/image.php/boost/core/1494448918/u/f1"
            }"""

    validJson.as[User] shouldBe a [Right[_, _]]
  }

  it should "match an equivalent User Case Class" in {
    val validJson =
      json"""{
        "id" : 2,
        "username" : "moodleadmin",
        "firstname" : "Moodle",
        "lastname" : "Admin",
        "fullname" : "Moodle Admin",
        "email" : "moodle@eckerd.edu",
        "department" : "",
        "firstaccess" : 1494445508,
        "lastaccess" : 1494518647,
        "auth" : "manual",
        "suspended" : false,
        "confirmed" : true,
        "lang" : "en",
        "theme" : "",
        "timezone" : "99",
        "mailformat" : 1,
        "description" : "",
        "descriptionformat" : 1,
        "city" : "St Petersburg",
        "country" : "US",
        "profileimageurlsmall" : "https://moodletest.eckerd.edu/moodle_kevin/theme/image.php/boost/core/1494448918/u/f2",
        "profileimageurl" : "https://moodletest.eckerd.edu/moodle_kevin/theme/image.php/boost/core/1494448918/u/f1"
        }"""

    val user = User(
      id = Some(2),
      username = Some("moodleadmin"),
      firstname = Some("Moodle"),
      lastname = Some("Admin"),
      fullname = Some("Moodle Admin"),
      email = Some("moodle@eckerd.edu"),
      department = Some(""),
      firstaccess = Some(1494445508),
      lastaccess = Some(1494518647),
      auth = Some("manual"),
      suspended = Some(false),
      confirmed = Some(true),
      lang = Some("en"),
      theme = Some(""),
      timezone = Some("99"),
      mailformat = Some(1),
      description = Some(""),
      descriptionformat = Some(1),
      city = Some("St Petersburg"),
      country = Some("US"),
      profileimageurlsmall = Some("https://moodletest.eckerd.edu/moodle_kevin/theme/image.php/boost/core/1494448918/u/f2"),
      profileimageurl = Some("https://moodletest.eckerd.edu/moodle_kevin/theme/image.php/boost/core/1494448918/u/f1")
    )

    validJson.as[User] shouldBe Right(user)
  }

  // Thank Moodle For This Hell
  it should "be able to parse a user with bad preferences" in {
    val invalidJson = json"""
      {
      "id" : 8,
      "username" : "testwebservice01",
      "firstname" : "Testy",
      "lastname" : "WebService",
      "fullname" : "Testy WebService",
      "email" : "nonsense01@nothing.com",
      "department" : "",
      "firstaccess" : 0,
      "lastaccess" : 1494518467,
      "auth" : "webservice",
      "suspended" : false,
      "confirmed" : true,
      "lang" : "en",
      "theme" : "",
      "timezone" : "99",
      "mailformat" : 1,
      "description" : "",
      "descriptionformat" : 1,
      "profileimageurlsmall" : "https://moodletest.eckerd.edu/moodle_kevin/theme/image.php/boost/core/1494525674/u/f2",
      "profileimageurl" : "https://moodletest.eckerd.edu/moodle_kevin/theme/image.php/boost/core/1494525674/u/f1",
      "preferences" : [
        {
          "name" : "auth_forcepasswordchange",
          "value" : "0"
        },
        {
          "name" : "email_bounce_count",
          "value" : "1"
        },
        {
          "name" : "email_send_count",
          "value" : "1"
        },
        {
          "name" : "_lastloaded",
          "value" : 1494531740
        }
      ]
    }"""

    invalidJson.as[User] shouldBe a [Right[_, _]]

  }

  "Users" should "be able to be decoded from Json" in {

    val validJson =
      json"""{
          "users" : [
            {
              "id" : 1,
              "username" : "guest",
              "firstname" : "Guest user",
              "lastname" : " ",
              "fullname" : "Guest user  ",
              "email" : "root@localhost",
              "department" : "",
              "firstaccess" : 0,
              "lastaccess" : 0,
              "auth" : "manual",
              "suspended" : false,
              "confirmed" : true,
              "lang" : "en",
              "theme" : "",
              "timezone" : "99",
              "mailformat" : 1,
              "description" : "This user is a special user that allows read-only access to some courses.",
              "descriptionformat" : 1,
              "profileimageurlsmall" : "https://moodletest.eckerd.edu/moodle_kevin/theme/image.php/boost/core/1494448918/u/f2",
              "profileimageurl" : "https://moodletest.eckerd.edu/moodle_kevin/theme/image.php/boost/core/1494448918/u/f1"
            },
            {
              "id" : 2,
              "username" : "moodleadmin",
              "firstname" : "Moodle",
              "lastname" : "Admin",
              "fullname" : "Moodle Admin",
              "email" : "moodle@eckerd.edu",
              "department" : "",
              "firstaccess" : 1494445508,
              "lastaccess" : 1494518647,
              "auth" : "manual",
              "suspended" : false,
              "confirmed" : true,
              "lang" : "en",
              "theme" : "",
              "timezone" : "99",
              "mailformat" : 1,
              "description" : "",
              "descriptionformat" : 1,
              "city" : "St Petersburg",
              "country" : "US",
              "profileimageurlsmall" : "https://moodletest.eckerd.edu/moodle_kevin/theme/image.php/boost/core/1494448918/u/f2",
              "profileimageurl" : "https://moodletest.eckerd.edu/moodle_kevin/theme/image.php/boost/core/1494448918/u/f1"
            }
          ],
          "warnings" : [
            {
              "item" : "",
              "warningcode" : "invalidfieldparameter",
              "message" : "The search key '' is not supported, look at the web service documentation"
            }
          ]
        }"""

    validJson.as[Users] shouldBe a [Right[_,_]]
  }

}
