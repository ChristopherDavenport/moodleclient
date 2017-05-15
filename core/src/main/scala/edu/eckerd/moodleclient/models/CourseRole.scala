package edu.eckerd.moodleclient.models

import cats.implicits._

import scala.util.Try

sealed trait CourseRole {
  import CourseRole._

  val value: Int = this match {
    case Manager => 1
    case Teacher => 3
    case NonEditingTeacher => 4
    case Student => 5
  }

  def fromValue(int: Int): Option[CourseRole] = int match {
    case 1 => Some(Manager)
    case 3 => Some(Teacher)
    case 4 => Some(NonEditingTeacher)
    case 5 => Some(Student)
    case _ => None
  }

  def fromValue(s: String): Option[CourseRole] = Try(s.toInt).toOption.flatMap(fromValue)
}

object CourseRole {
  case object Manager extends CourseRole
  case object Teacher extends  CourseRole
  case object NonEditingTeacher extends CourseRole
  case object Student extends CourseRole



}
