package services

import play.api.libs.json.Json
import scalaj.http.Http

import models.VkUser

/**
 * Created by meln1k on 23/08/14.
 */
object UserService {

  def getUser(uid: Long): VkUser = {
    val userInfoJson = Json.parse(
      Http("https://api.vk.com/method/users.get")
        .param("user_id",uid.toString)
        .param("fields","photo")
        .asString)

    val fistName = (userInfoJson \ "response") (0) \ "first_name" toString
    val lastName = (userInfoJson \ "response") (0) \ "last_name" toString
    val photoLink = (userInfoJson \ "response") (0) \ "photo" toString

    VkUser(uid, fistName, lastName, photoLink)
  }

  def getUserFriends(uid: Long): Seq[VkUser] = {
    val userFriendsJson = Json.parse(
      Http("https://api.vk.com/method/friends.get")
        .param("user_id",uid.toString)
        .asString)

    val friends = (userFriendsJson \ "response")
      .toString.filterNot(c => c == '[' || c == ']').split(",")
      .map(_.toLong).map(getUser)

    friends.toVector
  }




}
