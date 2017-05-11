package edu.eckerd.moodleclient

import org.http4s.UrlForm

trait MoodleAble[I, O] {
  def render(input : I): UrlForm
}
