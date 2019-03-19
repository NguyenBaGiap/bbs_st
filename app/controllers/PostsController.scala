package controllers

import java.io.File

import javax.inject._
import models._
import models.dto.PostsCreate
import play.api.mvc._
import skinny.Pagination
import ultil.{Message, Ultil}
import play.api.data._
import play.api.data.Forms._


class PostsController @Inject()(cc: MessagesControllerComponents,
                                authenticatedUserAction: AuthenticatedUserAction)
  extends AbstractController(cc) with play.api.i18n.I18nSupport {

  private val postsCreateUrl = routes.PostsController.create()

  val form: Form[PostsCreate] = Form(
    mapping(
      "title" -> nonEmptyText,
      "description" -> nonEmptyText,
      "body" -> nonEmptyText,
      "urlImg" -> optional(text),
    )(PostsCreate.apply)(PostsCreate.unapply)
  )


  def list(page: Int) = Action { implicit request =>
    // All users
    val users = User.findAll()
    // Pagination posts
    val listPosts = Posts.joins(Posts.userRef).paginate(Pagination.page(page).per(2))
      .orderBy(Posts.defaultAlias.id.desc).apply()
    val sumPageNum = Math.ceil(Posts.findAll().length / 2).toInt
    Ok(views.html.bbs.index(listPosts, users, sumPageNum, page))
  }

  def show(id: Long) = Action {
    val posts = Posts.joins(Posts.userRef).findById(id)
    val comments = Comment.joins(Comment.userRef).where('posts_id -> id).apply()
    if (posts.nonEmpty) Ok(views.html.bbs.posts_info(posts.get, comments)) else Ok(views.html.errors(Message.errors))
  }

  def add() = authenticatedUserAction { implicit request: Request[AnyContent] =>
    Ok(views.html.bbs.posts_add(form, postsCreateUrl))
  }

  def create() = Action { implicit request: Request[AnyContent] =>

    val errorFunction = { formWithErrors: Form[PostsCreate] =>
      BadRequest(views.html.bbs.posts_add(formWithErrors, postsCreateUrl))
    }

    val successFunction = { data: PostsCreate =>
      //save database
      val fileData = request.body.asMultipartFormData.get.file("urlImg")
      var fileName: String = ""
      if (fileData != None) {
        val file = fileData.get
        fileName = Ultil.createFileName(file.filename)
        println(fileName)
        val fileToPublic = new File(s"/Users/giap_nb/Desktop/Training/bbs_st/public/images/$fileName")
        file.ref.copyTo(fileToPublic, replace = true)
      }

      Posts.createWithAttributes(
        'title -> data.title,
        'description -> data.description,
        'body -> data.body,
        'userId -> 1,
        'urlImg -> Ultil.createUriFileDb(fileName)
      )

      Redirect(routes.PostsController.list()).flashing("info" -> "Posts added (trust me)")
    }

    val formValidationResult: Form[PostsCreate] = form.bindFromRequest
    formValidationResult.fold(
      errorFunction,
      successFunction
    )
  }
}
