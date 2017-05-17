package edu.eckerd.moodleclient.core.course

import org.http4s.UrlForm
import org.scalatest.{FlatSpec, Matchers}
import GetCourses.GetCoursesMoodleAble
import GetCategories.getCategoriesMoodleAble

class CourseSpec extends FlatSpec with Matchers {

  "GetCoursesMoodleAble" should "generate a UrlForm with the function name" in {
    val expected = UrlForm{
      ("wsfunction", "core_course_get_courses")
    }

    GetCoursesMoodleAble.render(GetCourses()) shouldBe expected
  }

  "GetCategoriesMoodleable" should "generate a UrlForm with the function name" in {
    val expected = UrlForm(("wsfunction", "core_course_get_categories"))
    getCategoriesMoodleAble.render(GetCategories()) shouldBe expected
  }

  "GetContentsMoodleAble" should "generate a urlform with the function name and id" in {
    val id = "id"
    val expected = UrlForm(
      ("wsfunction", "core_course_get_contents"),
      ("courseid", id)
    )

    GetContents.GetContentsMoodleAble.render(GetContents("id")) shouldBe expected
  }

}
