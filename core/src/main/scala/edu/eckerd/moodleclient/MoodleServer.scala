package edu.eckerd.moodleclient

import org.http4s.Uri
import org.http4s.client.Client
import org.http4s.dsl._


case class MoodleServer(
                         hostname: Uri,
                         instanceExtension: Option[String] = None
                       ) {
  val webserviceUrl: Uri = {
    val extension = instanceExtension.map(ext => "/" + cutSlashes(ext))
    hostname.withPath(extension.getOrElse("") + "/" + "webservice/rest/server.php")
  }

  val loginUrl: Uri = {
    val extension = instanceExtension.map(ext => "/" + cutSlashes(ext))
    hostname.withPath(extension.getOrElse("") + "/" + "login/token.php")
  }

  private def cutSlashes(s: String): String = {
    val t = if (s.endsWith("/")) s.substring(0, s.length - 1) else s
    if (t.startsWith("/")) t.substring(1, t.length) else t
  }

}
