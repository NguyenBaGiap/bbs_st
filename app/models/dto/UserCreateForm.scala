package models.dto

case class UserCreateForm(email:String,password:String,name: String,urlImg:Option[String])
