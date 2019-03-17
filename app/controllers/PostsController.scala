package controllers

import javax.inject._
import models._
import play.api.mvc._
import skinny.Pagination
import ultil.Message

class PostsController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def list(page: Int) = Action { implicit request =>
    // All users
    val users = User.findAll()
    // Pagination posts
    val listPosts = Posts.joins(Posts.userRef).paginate(Pagination.page(page).per(2))
      .orderBy(Posts.defaultAlias.id.asc).apply()
    val sumPageNum = Math.ceil(Posts.findAll().length / 2).toInt
    Ok(views.html.bbs.index(listPosts, users,sumPageNum,page))
  }

  def show(id: Long) = Action {
    val posts = Posts.joins(Posts.userRef).findById(id)
    val comments = Comment.joins(Comment.userRef).where('posts_id ->id).apply()
    if (posts.nonEmpty) Ok(views.html.bbs.posts_info(posts.get,comments)) else Ok(views.html.errors(Message.errors))
  }

  def add() = Action {
    Ok(views.html.bbs.posts_add())
  }
}
