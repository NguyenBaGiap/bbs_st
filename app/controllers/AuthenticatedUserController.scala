package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class AuthenticatedUserController @Inject()(
                                             cc: ControllerComponents,
                                             authenticatedUserAction: AuthenticatedUserAction
                                           ) extends AbstractController(cc) {

  def logout = authenticatedUserAction { implicit request: Request[AnyContent] =>
    Redirect(routes.UserController.login())
      .flashing("info" -> "You are logged out.")
      .withNewSession
  }

}

