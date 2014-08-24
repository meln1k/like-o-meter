package services

import play.api.libs.json._
import play.api.libs.functional.syntax._
import scalaj.http.{HttpOptions, Http}
import models._

/**
 * Created by meln1k on 23/08/14.
 */


object VkApiService {

  private implicit val vkUserReads: Reads[VkUser] = (
    (JsPath \ "id").read[Long] and
      (JsPath \ "first_name").read[String] and
      (JsPath \ "last_name").read[String]
    )(VkUser.apply _)

  def usersGet(userIds: Seq[String]) = {

    implicit val usersGetReads: Reads[UsersGetResult] =
      (JsPath \ "response").read[IndexedSeq[VkUser]]
        .map{s => UsersGetResult(s)}

    val res = Json.parse(
      Http("https://api.vk.com/method/users.get")
        .param("user_ids",userIds.mkString(","))
        .param("v","5.24")
        .option(HttpOptions.readTimeout(10000))
        .asString
    )

    res.validate[UsersGetResult].asOpt
  }

  def friendsGet(userId: Long) = {

    implicit val friendListReads: Reads[FriendList] = (
      (JsPath \ 'count).read[Int] and
        (JsPath \ 'items).read[IndexedSeq[VkUser]]
      )(FriendList.apply _)

    implicit val friendsGetReads: Reads[FriendsGetResult] =
      (JsPath \ "response").read[FriendList]
        .map(FriendsGetResult)

    val res = Json.parse(Http("https://api.vk.com/method/friends.get")
      .param("user_id",userId.toString)
      .param("fields", "id")
      .param("v","5.24")
      .option(HttpOptions.readTimeout(10000))
      .asString)

    res.validate[FriendsGetResult].asOpt
  }

  def likesGetList(typ: String, ownerId: Long, itemId: Long) = {

    implicit val likesListReads: Reads[LikesList] = (
      (JsPath \ 'count).read[Int] and
        (JsPath \ 'items).read[IndexedSeq[VkUser]]
      )(LikesList.apply _)

    implicit val likesGetListReads: Reads[LikesGetListResult] =
      (JsPath \ "response").read[LikesList]
        .map(LikesGetListResult)

    val res = Json.parse(
      Http("https://api.vk.com/method/likes.getList")
        .param("type", typ)
        .param("owner_id", ownerId.toString)
        .param("item_id", itemId.toString)
        .param("filter","likes")
        .param("extended", "1")
        .param("v","5.24")
        .option(HttpOptions.readTimeout(10000))
        .asString
    )

    res.validate[LikesGetListResult].asOpt
  }

  def photosGetAlbums(userId: Long) = {

    implicit val albumReads: Reads[Album] = (
      (JsPath \ "id").read[Long] and
        (JsPath \ "owner_id").read[Long]
      )(Album.apply _)

    implicit val albumListReads: Reads[AlbumList] = (
      (JsPath \ 'count).read[Int] and
        (JsPath \ 'items).read[IndexedSeq[Album]]
      )(AlbumList.apply _)

    implicit val photosGetAlbumsReads: Reads[PhotosGetAlbumsResult] =
      (JsPath \ "response").read[AlbumList]
        .map(PhotosGetAlbumsResult)

    val res = Json.parse(
      Http("https://api.vk.com/method/photos.getAlbums")
        .param("user_id", userId.toString)
        .param("need_system", "1")
        .param("v","5.24")
        .option(HttpOptions.readTimeout(10000))
        .asString
    )

    res.validate[PhotosGetAlbumsResult].asOpt
  }

  def photosGet(ownerId: Long, albumId: Long) = {

    implicit val photoReads: Reads[Photo] = (
      (JsPath \ "id").read[Long] and
        (JsPath \ "owner_id").read[Long] and
        (JsPath \ "likes" \ "count").read[Int]
      )(Photo.apply _)

    implicit val photosListReads: Reads[PhotosList] = (
      (JsPath \ 'count).read[Int] and
        (JsPath \ 'items).read[IndexedSeq[Photo]]
      )(PhotosList.apply _)

    implicit val photosGetAlbumsReads: Reads[PhotosGetResult] =
      (JsPath \ "response").read[PhotosList]
        .map(PhotosGetResult)

    val res = Json.parse(
      Http("https://api.vk.com/method/photos.get")
        .param("user_id", ownerId.toString)
        .param("album_id", albumId.toString)
        .param("extended", "1")
        .param("v","5.24")
        .option(HttpOptions.readTimeout(10000))
        .asString
    )
    res.validate[PhotosGetResult].asOpt
  }
}
