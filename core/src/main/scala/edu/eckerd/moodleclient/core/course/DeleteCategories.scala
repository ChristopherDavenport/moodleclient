package edu.eckerd.moodleclient.core.course

import cats.implicits._
import edu.eckerd.moodleclient.MoodleAble
import io.circe.Json
import org.http4s.UrlForm

case class DeleteCategories(categories: List[DeleteCategory])

object DeleteCategories {

  implicit val deleteCategoriesMoodleAble = new MoodleAble[DeleteCategories, Unit] {
    def render(input: DeleteCategories): UrlForm = renderCategories(input.categories)
  }

  def renderCategories(l: List[DeleteCategory]): UrlForm = {
    val function = List(("wsfunction", "core_course_delete_categories"))
    val array = l.zipWithIndex.flatMap{case (cat, index) => renderCategory(cat, index)}

    UrlForm(function ::: array :_*)
  }

  def renderCategory(deleteCategory: DeleteCategory, index: Int): List[(String, String)] = {
    val newparent = deleteCategory.newparent
      .map(np => List((s"categories[$index][newparent]", np.show)))
      .getOrElse(List.empty[(String, String)])
    val recursive = deleteCategory.recursive
      .map(b => List((s"categories[$index][recursive]", if (b) "1" else "0")))
      .getOrElse(List.empty[(String, String)])

    List((s"categories[$index][id]", deleteCategory.categoryId.show)) ::: newparent ::: recursive
  }

}
