package services

import javax.inject.Singleton
import models.User
import models.dto.UserLoginForm
import play.mvc.BodyParser.Empty

import scala.util._

@Singleton
class UserAuthServices {
  def lookupUser(userForm: UserLoginForm): Option[User] = {
     val user:Option[User] = User.findByEmail(userForm.email, userForm.password) match {
      case Failure(exception) => None
      case Success(result) => result
    }
    user
  }
}
