package services

import scalaj.http.Http

/**
 * Created by meln1k on 23/08/14.
 */
object VkApiService {
  def usersGet(userId: Long) = {
    Http("https://api.vk.com/method/users.get")
      .param("user_id",userId.toString)
      .param("fields","photo")
      .asString
  }

  def friendsGet(userId: Long) = {
    Http("https://api.vk.com/method/friends.get")
      .param("user_id",userId.toString)
      .asString
  }

  def likesGetList(typ: String, ownerId: Long, itemId: Long) = {
    Http("https://api.vk.com/method/likes.getList")
      .param("type", typ)
      .param("owner_id", ownerId.toString)
      .param("item_id", itemId.toString)
      .asString
  }

  def photosGetAlbums(userId: Long) = {
    Http("https://api.vk.com/method/photos.getAlbums")
      .param("user_id", userId.toString)
      .param("need_system", "1")
      .asString
  }

  def photosGet(ownerId: Long, albumId: Long) = {
    Http("https://api.vk.com/method/photos.get")
      .param("user_id", ownerId.toString)
      .param("album_id", albumId.toString)
      .param("extended", "1")
      .asString
  }
}
