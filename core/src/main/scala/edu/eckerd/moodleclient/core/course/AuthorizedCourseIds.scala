package edu.eckerd.moodleclient.core.course

import edu.eckerd.moodleclient.Token

case class AuthorizedCourseIds(token : Token, courseIds: List[String])
