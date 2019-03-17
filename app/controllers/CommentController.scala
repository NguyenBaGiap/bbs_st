package controllers

import javax.inject._
import play.api.mvc._
import models._

class CommentController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def list(postsId:Long) = Action{
    val lists = Comment.joins(Comment.userRef).where('posts_id -> postsId)
    println(">>>>>>>>>>>>>>")
    println(lists)
    Ok("skldnfsdk")
  }
}
