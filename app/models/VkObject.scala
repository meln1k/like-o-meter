package models

import play.api.libs.json._

/**
 * Created by meln1k on 24/08/14.
 */

abstract class User
case class VkUser(id: Long, firstName: String, lastName: String) extends User
case class VkUserWithPhoto(id: Long, firstName: String, lastName: String, photoLink: String) extends User
case class UsersGetResult(response: IndexedSeq[User])

case class FriendList(count: Int, items: IndexedSeq[VkUser])
case class FriendsGetResult(response: FriendList)

case class LikesList(count: Int, items: IndexedSeq[VkUser])
case class LikesGetListResult(response: LikesList)

case class Album(id: Long, ownerId: Long)
case class AlbumList(count: Int, items: IndexedSeq[Album])
case class PhotosGetAlbumsResult(response: AlbumList)

case class Photo(id: Long, ownerId: Long, likesCount: Int)
case class PhotosList(count: Int, items: IndexedSeq[Photo])
case class PhotosGetResult(response: PhotosList)