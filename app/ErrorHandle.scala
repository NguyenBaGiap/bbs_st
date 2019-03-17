import play.api.http.HttpErrorHandler
import play.api.mvc._
import play.api.mvc.Results._

import scala.concurrent._
import javax.inject.Singleton
import ultil.Message;

@Singleton
class ErrorHandler extends HttpErrorHandler {
  def onClientError(request: RequestHeader, statusCode: Int, message: String) = {
    Future.successful(
      statusCode match {
        case 400=> Ok(views.html.errors(Message.errors))
        case 401=> Ok(views.html.errors(Message.errors))
        case 404=> Ok(views.html.errors(Message.errors))
        case 500=> Ok(views.html.errors(Message.errors))
        case _ => Redirect("/")
      }
    )
  }

  def onServerError(request: RequestHeader, exception: Throwable) = {
    Future.successful(
      InternalServerError("A server error occurred: " + exception.getMessage)
    )
  }
}