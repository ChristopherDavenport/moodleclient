package edu.eckerd.moodleclient.core.course

import edu.eckerd.moodleclient.MoodleAble
import edu.eckerd.moodleclient.models.Category
import io.circe.Json
import org.http4s.UrlForm

case class GetCategories()

object GetCategories {

  implicit val getCategoriesMoodleAble = new MoodleAble[GetCategories, List[Category]] {
    override def render(input: GetCategories): UrlForm = UrlForm(("wsfunction", "core_course_get_categories"))
  }

}