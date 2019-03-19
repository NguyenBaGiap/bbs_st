package controllers

import java.io.File
import java.nio.file.Paths

import javax.inject._
import play.Application
import play.api.Play
import play.api.mvc._

class TestController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def test() = Action{
    Ok(views.html.test())
  }

  def uploadFile = Action(parse.multipartFormData) { request =>
    request.body.file("picture").map { picture =>

      val filename = Paths.get(picture.filename).getFileName
      val fileSize = picture.fileSize
      val contentType = picture.contentType
      val fileToPublic = new File(s"/Users/giap_nb/Desktop/Training/bbs_st/public/images/${filename}")

      picture.ref.copyTo(fileToPublic,replace = true)
      //picture.ref.moveFileTo(fileToPublic,replace = true)
      Ok("File uploaded")
    }.getOrElse {
      Redirect(routes.HomeController.index()).flashing("error" -> "Missing file")
    }
  }
}
