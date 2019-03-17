package models.dao

import javax.inject.Inject
import models.User

@javax.inject.Singleton
class UserDao @Inject()() {

  def lookupUser(u: User): Boolean = {
    //TODO query your database here
    if (u.email == "foo" && u.password == "foo") true else false
  }

}