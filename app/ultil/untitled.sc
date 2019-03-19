import java.util.Date

import scala.util.Random

val SESSION_USERNAME_ID = 0
val PREFIX_FILE_NAME:String = "BBS_"
val LENGTH_CREATE_RANDOM_STRING = 10
val PREFIX_URI_FILE = "http://localhost:9000/assets/images/"
val DEFAULT_FILE_URI = "http://localhost:9000/assets/images/onepiece01.jpg"

def getExtensionFile(fileName:String):String = fileName.substring(fileName.lastIndexOf("."))

def createFileName(fileName:String):String = {
  val date = new Date().getTime
  val str = Random.alphanumeric.take(LENGTH_CREATE_RANDOM_STRING).mkString("")
  PREFIX_FILE_NAME.concat(str.concat(date.toString)).concat(getExtensionFile(fileName))
}

def createUriFileDb(fileName:String):String = if(fileName != "") PREFIX_URI_FILE.concat(fileName) else DEFAULT_FILE_URI

createUriFileDb("")


