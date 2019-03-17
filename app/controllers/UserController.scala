package controllers

import javax.inject._
import models.dao.UserDao
import play.api.mvc._


class UserController @Inject()(
                                cc: ControllerComponents,
                                userDao: UserDao
                              )  extends AbstractController(cc) {

  def login = Action{
    Ok(views.html.bbs.login())
  }

  def detail(id:Long) = Action {
    Ok(views.html.bbs.user_detail())
  }
}
