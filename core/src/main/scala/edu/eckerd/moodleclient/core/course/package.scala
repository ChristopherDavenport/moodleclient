package edu.eckerd.moodleclient.core

import edu.eckerd.moodleclient.{MoodleFunction, Token}
import edu.eckerd.moodleclient.models.Course
import org.http4s.UrlForm

package object course {

  val getCourses : MoodleFunction[Token, List[Course]] = token => {
    UrlForm(("",""),("",""))
    UrlForm(("wstoken", token), ("wsfunction","core_course_get_courses"))
  }



}
