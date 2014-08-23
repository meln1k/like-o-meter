package models

/**
 * Created by meln1k on 23/08/14.
 */
abstract class Item
case class Album(val id: Long, val owner: VkUser) extends Item
case class Photo(val id: Long, val album: Album) extends Item
case class Video(val id: Long, val owner: VkUser) extends Item
case class WallPost(val id: Long, val owner: VkUser, val liked: Seq[VkUser]) extends Item
