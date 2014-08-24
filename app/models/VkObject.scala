package models

import play.api.libs.json._

/**
 * Created by meln1k on 24/08/14.
 */

abstract class User
case class VkUser(id: Long, firstName: String, lastName: String)
case class UsersGetResult(response: IndexedSeq[VkUser])

case class FriendList(count: Int, items: IndexedSeq[VkUser])
case class FriendsGetResult(response: FriendList)

case class LikesList(count: Int, items: IndexedSeq[VkUser])
case class LikesGetListResult(response: LikesList)

case class Album(id: Long, ownerId: Long)
case class AlbumList(count: Int, items: IndexedSeq[Album])
case class PhotosGetAlbumsResult(response: AlbumList)

abstract class Item

case class Photo(id: Long, ownerId: Long, likesCount: Int) extends Item
case class PhotosList(count: Int, items: IndexedSeq[Photo])
case class PhotosGetResult(response: PhotosList)

case class Post(id: Long, ownerId: Long, likesCount: Int) extends Item
case class PostsList(count: Int, items: IndexedSeq[Post])
case class WallGetResult(response: PostsList)