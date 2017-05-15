package edu.eckerd.moodleclient.core.course

import edu.eckerd.moodleclient.models.Course
import edu.eckerd.moodleclient.{MoodleAble, Token}
import org.http4s.UrlForm

case class GetCourses()

object GetCourses {

  implicit val GetCoursesMoodleAble = new MoodleAble[GetCourses, List[Course]] {
    def render(input: GetCourses): UrlForm = {
      UrlForm(
        ("wsfunction","core_course_get_courses")
      )
    }
  }
}
