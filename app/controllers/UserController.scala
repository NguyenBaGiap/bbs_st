package controllers

import javax.inject._
import models.User
import models.dto.UserCreateForm
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import ultil.{Message, Ultil}

@Singleton
class UserController @Inject()(
                                cc: ControllerComponents,
                                authenticatedUserAction: AuthenticatedUserAction
                              )  extends AbstractController(cc) with play.api.i18n.I18nSupport {

  private val userCreateUrl = routes.UserController.create()

  val form:Form[UserCreateForm] = Form(
    mapping(
      "email" -> nonEmptyText,
      "password" -> nonEmptyText,
      "name" -> nonEmptyText,
      "urlImg" -> optional(text),
    )(UserCreateForm.apply)(UserCreateForm.unapply)
  )

  def register = Action {implicit request:Request[AnyContent] =>
    Ok(views.html.bbs.user_register(form,userCreateUrl))
  }

  def create = Action {implicit request:Request[AnyContent] =>

    val errorFunction = {formWithErrors:Form[UserCreateForm] =>
      BadRequest(views.html.bbs.user_register(formWithErrors,userCreateUrl))
    }

    val successFunction = {data:UserCreateForm =>
      //save database
      val userId = User.createWithAttributes(
        'email->data.email,
        'password->data.password,
        'name->data.name
      )

      Redirect(routes.HomeController.index()).withSession(
        Ultil.SESSION_USERNAME_KEY -> data.email,
        Ultil.SESSION_USER_ID -> userId.toString
      )
    }

    val formValidationResult:Form[UserCreateForm] = form.bindFromRequest()
    formValidationResult.fold(
      errorFunction,
      successFunction
    )

  }

  def detail(id:Long) = Action {
    Ok(views.html.bbs.user_detail())
  }

  def logout = Action {implicit request:Request[AnyContent] =>
    Redirect(routes.PostsController.list())
      .flashing("info" -> Message.MESSAGE_LOGOUT)
      .withNewSession
  }
}
