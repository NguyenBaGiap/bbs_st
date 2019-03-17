package models

import scalikejdbc._
import skinny.orm._
import skinny.Pagination

case class Posts(
                  id:Long,
                  title:String,
                  body:String,
                  userId:Long,
                  description:String,
                  urlImg:String,
                  comments:Seq[Comment] = Nil,
                  user: Option[User] = None
                )
object Posts extends SkinnyCRUDMapper[Posts] {
  override def tableName = "posts"
  override lazy val defaultAlias = createAlias("p")
  override def columnNames = Seq("id", "title", "body", "user_id","description","url_img")

  override def extract(rs: WrappedResultSet, rn: ResultName[Posts]):Posts = Posts(
    id = rs.get(rn.id),
    title = rs.get(rn.title),
    body = rs.get(rn.body),
    userId = rs.get(rn.userId),
    description = rs.get(rn.description),
    urlImg = rs.get(rn.urlImg)
  )

  lazy val commentRef = hasMany[Comment](
    // association's SkinnyMapper and alias
    many = Comment -> Comment.defaultAlias,
    // defines join condition by using aliases
    on = (p, c) => sqls.eq(p.id, c.postsId),
    // function to merge associations to main entity
    merge = (posts, comments) => posts.copy(comments = comments)
  )

  lazy val userRef = belongsTo[User](User, (m, u) => m.copy(user = u)).byDefault

  val perPageNum = 2

  def paginateWithUser(userId:Long, currentPage:Int): Map[String,Any] = {
    val items = paginate(Pagination.page(currentPage).per(perPageNum)).where('user_id -> userId).orderBy(defaultAlias.id.desc).apply()
    val count = where('user_id -> userId).count()
    val sumPageNum = Math.ceil(count.toDouble / perPageNum.toDouble).toInt
    Map(
      "items" -> items,
      "paging" -> Map(
        "page" -> currentPage,
        "perPage" -> perPageNum,
        "count" -> count,
        "sumPageNum" -> sumPageNum
      )
    )
  }

  def paginate(currentPage:Int): Map[String,Any] = {
    val items = Posts.joins(Posts.userRef).paginate(Pagination.page(currentPage).per(perPageNum)).orderBy(defaultAlias.id.desc).apply()
    val count = findAll().length
    val sumPageNum = Math.ceil(count.toDouble / perPageNum.toDouble).toInt
    Map(
      "items" -> items,
      "paging" -> Map(
        "page" -> currentPage,
        "perPage" -> perPageNum,
        "count" -> count,
        "sumPageNum" -> sumPageNum
      )
    )
  }

}
