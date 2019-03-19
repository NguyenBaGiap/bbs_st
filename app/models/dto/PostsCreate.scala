package models.dto

case class PostsCreate(
                        title:String,
                        body:String,
                        description:String,
                        urlImg : Option[String]
                      )
