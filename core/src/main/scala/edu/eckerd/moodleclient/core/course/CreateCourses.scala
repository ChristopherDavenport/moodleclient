package edu.eckerd.moodleclient.core.course

import cats.Show
import edu.eckerd.moodleclient.MoodleAble
import edu.eckerd.moodleclient.models.Course
import io.circe.Json
import org.http4s.UrlForm
import cats.implicits._

case class CreateCourses(courses: List[Course])

object CreateCourses {

  implicit val CreateCourseMoodleAble: MoodleAble[CreateCourses, Json] = new MoodleAble[CreateCourses, Json] {
    def render(input: CreateCourses): UrlForm = {

      UrlForm(
       renderCourses(input.courses):_*
      )
    }
  }

  def renderCourses(courses: List[Course]): List[(String, String)] = {
    val function = ("wsfunction", "core_course_create_courses")
    val renderedCourses = courses.zipWithIndex.flatMap{case (course, index) => renderCourse(course, index)}

    function :: renderedCourses
  }

  def renderCourse(course: Course, index: Int): List[(String, String)] = {
    def renderOptValue[A](call: String, value: Option[A])(implicit show: Show[A]): List[(String, String)] = {
      value.map(a => List((call, a.show)))
      .getOrElse(List.empty[(String, String)])
    }

    val permanent = List(
      (s"courses[$index][fullname]", course.fullname),
      (s"courses[$index][shortname]", course.shortname)
    )

    val categoryid = renderOptValue(s"courses[$index][categoryid]", course.categoryid)
    val idNumber = renderOptValue(s"courses[$index][idnumber]" ,course.idnumber)
    val summary = renderOptValue(s"courses[$index][idnumber]", course.summary)
    val summaryFormat = renderOptValue(s"courses[$index][summaryformat]", course.summaryformat)
    val format = renderOptValue(s"courses[$index][format]", course.format)
    val showgrades = renderOptValue(s"courses[$index][showgrades]", course.showgrades)
    val newsitems = renderOptValue(s"courses[$index][newsitems]", course.newsitems)
    val startdate = renderOptValue(s"courses[$index][startdate]", course.startdate)
    val enddate = renderOptValue(s"courses[$index][enddate]", course.enddate)
    val numsections = renderOptValue(s"courses[$index][numsections]", course.numsections)
    val maxbytes = renderOptValue(s"courses[$index][maxbytes]", course.maxbytes)
    val showreports = renderOptValue(s"courses[$index][showreports]", course.showreports)
    val visible = renderOptValue(s"courses[$index][visible]", course.visible)
//    val hiddensections = renderOptValue(s"courses[$index][hiddensections]", course.hiddensections)
    val groupmode = renderOptValue(s"courses[$index][groupmode]", course.groupmode)
    val groupmodeforce = renderOptValue(s"courses[$index][groupmodeforce]", course.groupmodeforce)
    val defaultgroupingid = renderOptValue(s"courses[$index][defaultgroupingid]", course.defaultgroupingid)
    val enablecompletion = renderOptValue(s"courses[$index][enablecompletion]", course.enablecompletion)
    val completionnotify = renderOptValue(s"courses[$index][completionnotify]", course.completionnotify)
    val lang = renderOptValue(s"courses[$index][lang]", course.lang)
    val forcetheme = renderOptValue(s"courses[$index][forcetheme]", course.forcetheme)


    permanent ::: categoryid ::: idNumber ::: summary ::: summaryFormat ::: format ::: showgrades ::: newsitems :::
    startdate ::: enddate ::: numsections ::: maxbytes ::: showreports ::: visible ::: groupmode ::: groupmodeforce :::
    defaultgroupingid ::: enablecompletion ::: completionnotify ::: lang ::: forcetheme
  }

//    s"courses[$arrayVal][courseformatoptions][0][name]= string
//    s"courses[$arrayVal][courseformatoptions][0][value]= string

}
