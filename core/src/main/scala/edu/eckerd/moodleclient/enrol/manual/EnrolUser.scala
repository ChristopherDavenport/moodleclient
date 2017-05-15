package edu.eckerd.moodleclient.enrol.manual

import edu.eckerd.moodleclient.MoodleAble
import edu.eckerd.moodleclient.models.CourseRole
import org.http4s.UrlForm

case class EnrolUser(
                      roleid: CourseRole,
                      userid: Int,
                      courseid: Int,
                      timestart: Option[Int] = None,
                      timeend: Option[Int] = None,
                      suspend: Option[Boolean] = None
                    )

object EnrolUser {
  implicit val enrolUserMoodleAble = new MoodleAble[EnrolUser, Unit] {
    override def render(input: EnrolUser): UrlForm = EnrolUsers.renderUsers(List(input))
  }

}
