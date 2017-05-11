package edu.eckerd.moodleclient.core

import edu.eckerd.moodleclient.{MoodleFunction, Token}
import edu.eckerd.moodleclient.models.Course
import io.circe.Json
import org.http4s.UrlForm

package object course {

//  val getCourses : MoodleFunction[Token, List[Course]] = token => {
//
//  }

  val getActivitiesOverview : MoodleFunction[AuthorizedCourseIds, Json] = aci => {
      UrlForm(
        Map(
          "wstoken" -> List(aci.token),
          "wsfunction" -> List("core_course_get_activities_overview"),
          "courseids[]" -> aci.courseIds
        )
      )
  }

  val getCategories : MoodleFunction[Token, Json] = token => {
    UrlForm(
      Map(
        "wstoken" -> List(token),
        "wsfunction" -> List("core_course_get_categories")
      )
    )
  }
//
//  val getContents : MoodleFunction[AuthorizedCourseId, Json] = aci => {
//
//  }

  val getCourseModule: MoodleFunction[AuthorizedCourseModule, Json] = aci => {
    UrlForm(
      Map(
        "wstoken" -> List(aci.token),
        "wsfunction" -> List("core_course_get_course_module"),
        "cmid" -> List(aci.courseModule)
      )
    )
  }

  val getCourseModuleByInstance: MoodleFunction[AuthorizedCourseModule, Json] = aci => {
    UrlForm(
      Map(
        "wstoken" -> List(aci.token),
        "wsfunction" -> List("core_course_get_course_module"),
        "cmid" -> List(aci.courseModule)
      )
    )
  }


}
