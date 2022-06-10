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

  def createUniversity = Action.async{ implicit rs =>
    universityDao.all().map(_ => Ok(views.html.createUniversityForm(universityForm)))
  }

  def editUniversity(name: String) = Action.async{ implicit rs =>
    val universities = for {
      university <- universityDao.findByName(name)
    } yield university

    universities.map{
      case university =>
        university match {
          case Some(s) => Ok(views.html.editUniversityForm(name, universityForm.fill(s)))
          case None => NotFound
        }
    }
  }

  def saveUniversity = Action.async{ implicit rs =>
    universityForm.bindFromRequest().fold(
      formWithError => universityDao.all().map(_ => BadRequest(views.html.createUniversityForm(formWithError))),
      university => {
        for {
          _ <- universityDao.insert(university)
        } yield Redirect(routes.UniversityController.index)
      }
    )
  }

  def updateUniversity(name: String) = Action.async{ implicit rs =>
    universityForm.bindFromRequest().fold(
      formWithError => universityDao.all().map(_ => BadRequest(views.html.editUniversityForm(name, formWithError))),
      university => {
        for {
          - <- universityDao.update(name, university)
        } yield Redirect(routes.UniversityController.index)
      }
    )
  }

  def deleteUniversity(name: String) = Action.async{ implicit  rs =>
    for {
      _ <- universityDao.delete(name)
    } yield Redirect(routes.UniversityController.index)
  }
}
