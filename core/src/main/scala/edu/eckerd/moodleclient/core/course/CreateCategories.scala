package edu.eckerd.moodleclient.core.course

import edu.eckerd.moodleclient.models.{Category, CreatedCategory}
import org.http4s.UrlForm
import cats.implicits._
import edu.eckerd.moodleclient.MoodleAble

case class CreateCategories(categories: List[Category])

object CreateCategories {

  implicit val createCategoriesMoodleAble = new MoodleAble[CreateCategories, List[CreatedCategory]] {
    override def render(input: CreateCategories): UrlForm = renderCategories(input.categories)
  }


  def renderCategories(l: List[Category]): UrlForm = {
    UrlForm(
    (
      "wsfunction", "core_course_create_categories") ::
      l.zipWithIndex.flatMap{case (cat, index) => renderCategory(cat, index)} :_*
    )
  }

  def renderCategory(category: Category, index: Int): List[(String, String)] = {
    val name = List((s"categories[$index][name]", category.name))
    val parent = category.parent.map(p => List((s"categories[$index][parent]", p.show))).getOrElse(List.empty[(String, String)])
    val idnumber = category.idnumber.map(i => List((s"categories[$index][idnumber]", i))).getOrElse(List.empty[(String, String)])
    val description = category.description.map(d => List((s"categories[$index][description]", d))).getOrElse(List.empty[(String, String)])
    val descriptionFormat = category.descriptionformat
      .map(df => List((s"categories[$index][descriptionformat]", df.show))).getOrElse(List.empty[(String, String)])
    val theme = category.theme.map(t => List((s"categories[$index][theme]", t))).getOrElse(List.empty[(String, String)])

    name ::: parent ::: idnumber ::: description ::: descriptionFormat ::: theme
  }

}
