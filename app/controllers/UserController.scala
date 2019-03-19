package controllers

import javax.inject._
import play.api.mvc._
import ultil.Message

@Singleton
class UserController @Inject()(
                                cc: ControllerComponents,
                                authenticatedUserAction: AuthenticatedUserAction
                              )  extends AbstractController(cc) {

  def detail(id:Long) = Action {
    Ok(views.html.bbs.user_detail())
  }

  def logout = authenticatedUserAction {implicit request:Request[AnyContent] =>
    Redirect(routes.HomeController.index())
      .flashing("info" -> Message.MESSAGE_LOGOUT)
      .withNewSession
  }
}
