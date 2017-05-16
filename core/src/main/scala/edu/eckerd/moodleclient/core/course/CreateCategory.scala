package edu.eckerd.moodleclient.core.course

import edu.eckerd.moodleclient.MoodleAble
import edu.eckerd.moodleclient.models.{Category, CreatedCategory}
import org.http4s.UrlForm

case class CreateCategory(category: Category)

object CreateCategory{
  implicit val createCategoryMoodleAble = new MoodleAble[CreateCategory, List[CreatedCategory]] {
    override def render(input: CreateCategory): UrlForm = CreateCategories.renderCategories(List(input.category))
  }
}