package models

import scalikejdbc._
import skinny.orm._

case class Comment(
                  id:Long,
                  content:String,
                  postsId:Long,
                  userId:Long,
                  user:Option[User] = None,
                  posts: Option[Posts] = None
                  )

object Comment extends SkinnyCRUDMapper[Comment] {
  override def tableName = "comments"
  override lazy val defaultAlias = createAlias("c")
  override def extract(rs: WrappedResultSet, rn: ResultName[Comment]):Comment = Comment(
    id = rs.get(rn.id),
    content = rs.get(rn.content),
    postsId = rs.get(rn.postsId),
    userId = rs.get(rn.userId)
  )

  lazy val userRef = belongsTo[User](User, (t, user) => t.copy(user = user))

  lazy val postsRef = belongsTo[Posts](Posts, (t, posts) => t.copy(posts = posts))
}
