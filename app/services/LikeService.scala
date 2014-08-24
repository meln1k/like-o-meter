package services

import models._
import play.api.Logger
import play.api.libs.json.{JsArray, Json}



/**
 * Created by meln1k on 23/08/14.
// */
object LikeService {
  def getUsersLikedByUser(user: VkUser): Seq[(VkUser, Int)] = {
  ???
  }

  def getUsersWhoLikedUser(user: VkUser): Seq[(VkUser, Int)] = {
    val userPhotos = EntityService.getUserPhotos(user).filter(_.likesCount != 0)
    val userPosts = EntityService.getUserPosts(user).filter(_.likesCount != 0)

    val userPhotoLiked = userPhotos flatMap EntityService.getLikedUsers
    val userPostsLiked = userPosts flatMap EntityService.getLikedUsers
    
    (userPhotoLiked ++ userPostsLiked).groupBy(l => l).map(t => (t._1, t._2.length)).toVector.sortBy(_._2)
  }

}
