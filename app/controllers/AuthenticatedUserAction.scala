package controllers

import javax.inject.Inject
import play.api.mvc.Results._
import play.api.mvc._
import ultil.{Message, Ultil}

import scala.concurrent.{ExecutionContext, Future}

class AuthenticatedUserAction @Inject() (parser: BodyParsers.Default)(implicit ec: ExecutionContext)
  extends ActionBuilderImpl(parser) {

  override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
    val maybeUsername = request.session.get(Ultil.SESSION_USERNAME_KEY)
    maybeUsername match {
      case None => {
        Future.successful(
          Ok(views.html.errors(Message.ERROR_AUTH_POSTS))
        )

      }
      case Some(u) => {
        val res: Future[Result] = block(request)
        res
      }
    }
  }
}
