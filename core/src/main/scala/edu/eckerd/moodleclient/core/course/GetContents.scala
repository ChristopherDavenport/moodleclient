package edu.eckerd.moodleclient.core.course

import edu.eckerd.moodleclient._
import io.circe.Json
import org.http4s.UrlForm

case class GetContents(courseId: String)

object GetContents {
  implicit val GetContentsMoodleAble = new MoodleAble[GetContents, Json] {
    def render(input: GetContents): UrlForm = {
      UrlForm(
        Map(
          "wsfunction" -> List("core_course_get_contents"),
          "courseid" -> List(input.courseId)
        )
      )
    }
  }
}