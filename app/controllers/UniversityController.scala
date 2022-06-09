package controllers

import dao.UniversityDAO
import model.University

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UniversityController @Inject()(
                                      universityDao: UniversityDAO,
                                      mcc: MessagesControllerComponents
                                    ) (implicit executionContext: ExecutionContext) extends MessagesAbstractController(mcc){

  val universityForm: Form[University] = Form(
    mapping(
      "name" -> text(),
      "rank" -> number
    )(University.apply)(University.unapply)
  )

  def index = Action.async{ implicit request =>
    universityDao.all().map{
      case univesities => Ok(views.html.universityIndex(universityForm, univesities))
    }
  }


}
