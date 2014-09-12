package models

import play.api.libs.json._

/**
 * Created by meln1k on 24/08/14.
 */

sealed trait VkObject

case class VkUser(id: Long, firstName: String, lastName: String) extends VkObject

case class UsersGetResult(response: IndexedSeq[VkUser]) extends VkObject

case class FriendList(count: Int, items: IndexedSeq[VkUser]) extends VkObject

case class FriendsGetResult(response: FriendList) extends VkObject

case class LikesList(count: Int, items: IndexedSeq[VkUser]) extends VkObject

case class LikesGetListResult(response: LikesList) extends VkObject

case class Album(id: Long, ownerId: Long) extends VkObject

case class AlbumList(count: Int, items: IndexedSeq[Album]) extends VkObject

case class PhotosGetAlbumsResult(response: AlbumList) extends VkObject

case class Photo(id: Long, ownerId: Long, likesCount: Int) extends VkObject

case class PhotosList(count: Int, items: IndexedSeq[Photo]) extends VkObject

case class PhotosGetResult(response: PhotosList) extends VkObject

case class Post(id: Long, ownerId: Long, likesCount: Int) extends VkObject

case class PostsList(count: Int, items: IndexedSeq[Post]) extends VkObject

case class WallGetResult(response: PostsList) extends VkObject