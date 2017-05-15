package edu.eckerd.moodleclient.core.course

import edu.eckerd.moodleclient.MoodleAble
import edu.eckerd.moodleclient.models.Course
import io.circe.Json
import org.http4s.UrlForm
import cats.implicits._

case class CreateCourses(courses: List[Course])

object CreateCourses {

  implicit val CreateCourseMoodleAble: MoodleAble[CreateCourses, Json] = new MoodleAble[CreateCourses, Json] {
    def render(input: CreateCourses): UrlForm = {
      val functionMap = Map(
        "wsfunction" -> List("core_course_create_courses")
      )

      UrlForm(
        functionMap ++ createCoursesMap(input.courses)
      )
    }
  }

  private def createCoursesMap(
                        list: List[Course],
                        start: Int = 0,
                        acc: List[Map[String, Seq[String]]] = List.empty[Map[String, Seq[String]]]
                      ): Map[String, Seq[String]] = {
    list match {
      case x :: xs =>
        createCoursesMap(xs, start+1, createCourseMap(x, start) :: acc)
      case Nil =>
        acc.foldLeft(Map.empty[String, Seq[String]]) { (acc, m) =>acc ++ m}
    }

  }

  private def createCourseMap(
                               course: Course,
                               arrayVal: Int
                             ): Map[String, Seq[String]] = {

    val permanent = Map(
      s"courses[$arrayVal][fullname]" -> List(course.fullname),
      s"courses[$arrayVal][shortname]" -> List(course.shortname)
    )

    val category = course.categoryid.map( c => Map(s"courses[$arrayVal][categoryid]" -> List(c.show))).getOrElse(Map.empty[String, Seq[String]])
    val idNumber = course.idnumber.map(id => Map(s"courses[$arrayVal][idnumber]" -> List(id.show))).getOrElse(Map.empty[String, Seq[String]])
    permanent ++ category ++ idNumber
  }
//
//    s"courses[$arrayVal][summary]" ->
//    s"courses[$arrayVal][summaryformat]" ->
//    s"courses[$arrayVal][format]" ->
//    s"courses[$arrayVal][showgrades]" ->
//    s"courses[$arrayVal][newsitems]" ->
//    s"courses[$arrayVal][startdate]" ->
//    s"courses[$arrayVal][enddate]" ->
//    s"courses[$arrayVal][numsections]" ->
//    s"courses[$arrayVal][maxbytes]" ->
//    s"courses[$arrayVal][showreports]" ->
//    s"courses[$arrayVal][visible]" ->
//    s"courses[$arrayVal][hiddensections]" ->
//    s"courses[$arrayVal][groupmode]" ->
//    s"courses[$arrayVal][groupmodeforce]" ->
//    s"courses[$arrayVal][defaultgroupingid]" ->
//    s"courses[$arrayVal][enablecompletion]" ->
//    s"courses[$arrayVal][completionnotify]" ->
//    s"courses[$arrayVal][lang]" ->
//    s"courses[$arrayVal][forcetheme]" ->
//
//    s"courses[$arrayVal][courseformatoptions][0][name]= string
//    s"courses[$arrayVal][courseformatoptions][0][value]= string

}
