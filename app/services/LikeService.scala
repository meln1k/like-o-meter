package services

import models._
import play.api.libs.json.Json



/**
 * Created by meln1k on 23/08/14.
// */
object LikeService {
  def getUsersLikedByUser(user: VkUser): Seq[(VkUser, Long)] = {
  ???
  }

  def getUsersWhoLikedUser(user: VkUser): Map[VkUser, Int] = {
    val userAlbums = Json.parse(VkApiService.photosGetAlbums(user.uid)) \\ "aid" map(a => Album(a.toString.toLong, user))
    val userPhotos = userAlbums.flatMap(
      a => Json.parse(VkApiService.photosGet(user.uid, a.id)) \\ "pid" map(p => Photo(p.toString.toLong, a))
    )
    val userPhotoLiked = userPhotos flatMap getLiked
    userPhotoLiked.groupBy(l => l).map(t => (t._1, t._2.length))
  }

  def getUsersWhoLikedUser(userId: Long): Map[VkUser, Int] = {
    getUsersWhoLikedUser(UserService.getUser(userId))
  }

  def getLiked(item: Item): Seq[VkUser] = {
    item match {
      case Photo(id, album) => (Json.parse(VkApiService.likesGetList("photo", album.owner.uid, id)) \\ "users")(0)
        .toString.filterNot(c => c == '[' || c == ']').split(",").map(_.toLong).map(UserService.getUser)
    }
  }

}
