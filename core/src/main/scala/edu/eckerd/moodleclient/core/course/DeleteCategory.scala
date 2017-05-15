package edu.eckerd.moodleclient.core.course

import edu.eckerd.moodleclient.MoodleAble
import io.circe.Json
import org.http4s.UrlForm

case class DeleteCategory(
                           categoryId: Int,
                           newparent: Option[Int] = None,
                           recursive: Option[Boolean] = None
                         )

object DeleteCategory {

  implicit val deleteCategoryMoodleAble = new MoodleAble[DeleteCategory, Unit] {
    def render(input: DeleteCategory): UrlForm = DeleteCategories.renderCategories(List(input))
  }

}
