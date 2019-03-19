package controllers

import javax.inject._
import models.dto.UserLoginForm
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import services.UserAuthServices
import ultil.{Message, Ultil}

class LoginController @Inject()(cc: MessagesControllerComponents,
                                userAuth:UserAuthServices) extends AbstractController(cc) with play.api.i18n.I18nSupport {

  val form: Form[UserLoginForm] = Form (
    mapping(
      "email" -> nonEmptyText,
      "password" -> nonEmptyText
    )(UserLoginForm.apply)(UserLoginForm.unapply)
  )

  private val formSubmitUrl = routes.LoginController.doLogin()

  def login = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.bbs.login(form,formSubmitUrl))
  }

  def doLogin() = Action { implicit request: Request[AnyContent] =>

    val errorFunction = { formWithErrors: Form[UserLoginForm] =>
      // form validation/binding failed...
      BadRequest(views.html.bbs.login(formWithErrors, formSubmitUrl))
    }
    val successFunction = { user: UserLoginForm =>
      // form validation/binding succeeded ...
      val foundUser = userAuth.lookupUser(user)
      if (foundUser != None) {
        Redirect(routes.HomeController.index()).withSession(
          Ultil.SESSION_USERNAME_KEY -> foundUser.get.email,
          Ultil.SESSION_USER_ID -> foundUser.get.id.toString
        )
      } else {
        Redirect(routes.LoginController.login())
          .flashing("error" -> s"${Message.ERROR_LOGIN}")
      }
    }
    val formValidationResult: Form[UserLoginForm] = form.bindFromRequest
    formValidationResult.fold(
      errorFunction,
      successFunction
    )
  }
}
