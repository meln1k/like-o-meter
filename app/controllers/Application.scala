package controllers

import play.api._
import play.api.mvc._
import services.{LikeService,EntityService}

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  def user(userId: String) = Action {
    val user = EntityService.getUser(userId)
    user.fold{Ok(views.html.index("User is unknown"))} { user =>
      Ok(views.html.index(LikeService.getUsersWhoLikedUser(user).toString()))
    }
  }

}