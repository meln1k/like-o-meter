package controllers

import play.api._
import play.api.mvc._
import services.{LikeService,EntityService}
import play.api.data._
import play.api.data.Forms._

object Application extends Controller {

  val idForm: Form[Id] = Form(
    mapping(
      "userId" -> nonEmptyText
    )(Id.apply)(Id.unapply)
  )

  def index = Action { implicit request =>
    Ok(views.html.index(idForm))
  }

  def submitUserId = Action { implicit request =>
    idForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(errors)),
      data => Redirect(routes.Application.showLikes(data.userId))
    )
  }

  def showLikes(userId: String) = Action {
    val user = EntityService.getUser(userId)
    user.fold {
      Ok(views.html.index(idForm))
    } { user =>
      Ok(views.html.userLikedResult(LikeService.getUsersWhoLikedUser(user)))
    }
  }

  case class Id(userId: String)

}