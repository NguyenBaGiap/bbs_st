package models

import scalikejdbc._
import skinny.orm._

import scala.util.Try

case class User(
               id:Long = 0,
               email:String,
               password:String,
               name:String = "",
               urlImg:String = "",
               role:Boolean = false,
               posts:Seq[Posts] = Nil,
               comments:Seq[Comment] = Nil
               )

object User extends SkinnyCRUDMapper[User] {
  override def tableName = "users"
  override def columnNames = Seq("id", "name", "email", "password","role","url_img")
  override lazy val defaultAlias = createAlias("u")
  private[this] lazy val u = defaultAlias

  override def extract(rs: WrappedResultSet, rn: ResultName[User]):User = User(
    id = rs.get(rn.id),
    email = rs.get(rn.email),
    password = rs.get(rn.password),
    role = rs.get(rn.role),
    urlImg=rs.get(rn.urlImg),
    name = rs.get(rn.name)
  )

  lazy val postsRef = hasMany[Posts](
    // association's SkinnyMapper and alias
    many = Posts -> Posts.defaultAlias,
    // defines join condition by using aliases
    on = (u, p) => sqls.eq(u.id, p.userId),
    // function to merge associations to main entity
    merge = (user, posts) => user.copy(posts = posts)
  )

  lazy val commentRef = hasMany[Comment](
    // association's SkinnyMapper and alias
    many = Comment -> Comment.defaultAlias,
    // defines join condition by using aliases
    on = (u, c) => sqls.eq(u.id, c.userId),
    // function to merge associations to main entity
    merge = (user, comments) => user.copy(comments = comments)
  )
  def findByEmail(email: String,
             password: String)(implicit s: DBSession = autoSession): Try[Option[User]] =
    Try {
      User.findBy(
        sqls
          .eq(u.column("email"), email)
          .and
          .eq(u.column("password"), password)
      )
    }
}
