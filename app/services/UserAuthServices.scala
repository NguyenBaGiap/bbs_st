package services

import javax.inject.Singleton
import models.User
import models.dto.UserLoginForm

@Singleton
class UserAuthServices {
  def lookupUser(u: UserLoginForm): Boolean = {

    val userDb = User.findByEmail(u.email,u.password)
    if (userDb.isSuccess && userDb.toOption.nonEmpty){
      val user = userDb.get.get
      if(u.email.equals(user.email) && u.password.equals(user.password)) true else false

    } else false
  }
}
