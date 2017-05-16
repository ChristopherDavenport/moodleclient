package edu.eckerd.moodleclient.core.course

import org.http4s.UrlForm
import org.scalatest.{FlatSpec, Matchers}
import GetCourses.GetCoursesMoodleAble

class CourseSpec extends FlatSpec with Matchers {

  "GetCoursesMoodleAble" should "generate a UrlForm with the function name" in {
    val expected = UrlForm{
      ("wsfunction", "core_course_get_courses")
    }

    GetCoursesMoodleAble.render(GetCourses()) shouldBe UrlForm()
  }

}
