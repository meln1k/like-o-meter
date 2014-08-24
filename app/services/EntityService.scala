package services

import models.{Photo, Item, VkUser}

/**
 * Created by meln1k on 24/08/14.
 */
object EntityService {

  def getUser(userId: String) = VkApiService.usersGet(Seq(userId)).map(_.response.head)

  def getUsers(userIds: Seq[String]) = VkApiService.usersGet(userIds).map(_.response)

  def getUserFriends(user: VkUser) = VkApiService.friendsGet(user.id).get.response.items

  def getUserPhotos(user: VkUser) = {
    val userAlbums = VkApiService.photosGetAlbums(user.id).get.response.items
    val userPhotos = userAlbums.flatMap(a => VkApiService.photosGet(a.ownerId, a.id).get.response.items)
    userPhotos
  }

  def getLikedUsers(item: Item): Seq[VkUser] = {
    item match {
      case Photo(id, ownerId, _) => VkApiService.likesGetList("photo", ownerId, id).get.response.items
    }
  }

}
