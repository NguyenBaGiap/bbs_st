package controllers

import javax.inject._
import models._
import models.dao.PostsCreate
import play.api.mvc._
import skinny.Pagination
import ultil.Message
import play.api.data._
import play.api.data.Forms._


class PostsController @Inject()(cc: MessagesControllerComponents) extends MessagesAbstractController(cc) {
  private val postsCreateUrl = routes.PostsController.create()

  val form:Form[PostsCreate] = Form(
    mapping(
      "title" -> nonEmptyText,
      "description" -> nonEmptyText,
      "body" -> nonEmptyText,
      "urlImg" -> text,
    )(PostsCreate.apply)(PostsCreate.unapply)
  )


  def list(page: Int) = Action { implicit request =>
    // All users
    val users = User.findAll()
    // Pagination posts
    val listPosts = Posts.joins(Posts.userRef).paginate(Pagination.page(page).per(2))
      .orderBy(Posts.defaultAlias.id.desc).apply()
    val sumPageNum = Math.ceil(Posts.findAll().length / 2).toInt
    Ok(views.html.bbs.index(listPosts, users,sumPageNum,page))
  }

  def show(id: Long) = Action {
    val posts = Posts.joins(Posts.userRef).findById(id)
    val comments = Comment.joins(Comment.userRef).where('posts_id ->id).apply()
    if (posts.nonEmpty) Ok(views.html.bbs.posts_info(posts.get,comments)) else Ok(views.html.errors(Message.errors))
  }

  def add() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.bbs.posts_add(form,postsCreateUrl))
  }

  def create() = Action { implicit request: MessagesRequest[AnyContent] =>
    val errorFunction = { formWithErrors: Form[PostsCreate] =>
      // this is the bad case, where the form had validation errors.
      // show the user the form again, with the errors highlighted.
      BadRequest(views.html.bbs.posts_add(formWithErrors, postsCreateUrl))
    }

    val successFunction = { data: PostsCreate =>
      // this is the SUCCESS case, where the form was successfully parsed as a BlogPost
      //save database
      Posts.createWithAttributes(
        'title -> data.title,
        'description -> data.description,
        'body -> data.body,
        'userId -> 1,
        'urlImg -> data.urlImg
      )
      Redirect(routes.PostsController.list()).flashing("info" -> "Posts added (trust me)")
    }

    val formValidationResult: Form[PostsCreate] = form.bindFromRequest
    formValidationResult.fold(
      errorFunction,   // sad case
      successFunction  // happy case
    )
  }
}
