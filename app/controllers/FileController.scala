package controllers

import java.nio.file.Paths

import javax.inject._
import play.api.mvc._

class FileController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def test() = Action{
    Ok(views.html.test())
  }

  def uploadFile = Action(parse.multipartFormData) { request =>
    request.body.file("picture").map { picture =>

      // only get the last part of the filename
      // otherwise someone can send a path like ../../home/foo/bar.txt to write to other files on the system
      println(picture)
      val filename = Paths.get(picture.filename).getFileName
      val fileSize = picture.fileSize
      val contentType = picture.contentType

      picture.ref.copyTo(Paths.get(s"/tmp/picture/$filename"), replace = true)
      Ok("File uploaded")
    }.getOrElse {
      Redirect(routes.HomeController.index).flashing("error" -> "Missing file")
    }
  }
}
