package controllers

import dao.UnivMatchDAO
import model.UnivMatch

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class MatchController @Inject()(
                                 univMatchDao: UnivMatchDAO,
                                 mcc: MessagesControllerComponents
                               ) (implicit executionContext: ExecutionContext) extends MessagesAbstractController(mcc){

  val univMatchForm: Form[UnivMatch] = Form(
    mapping(
      "shortname" -> text(),
      "name" -> text()
    )(UnivMatch.apply)(UnivMatch.unapply)
  )

  def univMatchIndex = Action.async{ implicit request =>
    univMatchDao.all().map{
      case univesities => Ok(views.html.univMatchIndex(univMatchForm, univesities))
    }
  }

  def createUniversity = Action.async{ implicit rs =>
    univMatchDao.all().map(_ => Ok(views.html.createUnivMatchForm(univMatchForm)))
  }

  def editUniversity(name: String) = Action.async{ implicit rs =>
    val universities = for {
      university <- univMatchDao.findByName(name)
    } yield university

    universities.map{
      case university =>
        university match {
          case Some(s) => Ok(views.html.editUnivMatchForm(name, univMatchForm.fill(s)))
          case None => NotFound
        }
    }
  }

  def saveUniversity = Action.async{ implicit rs =>
    univMatchForm.bindFromRequest().fold(
      formWithError => univMatchDao.all().map(_ => BadRequest(views.html.createUnivMatchForm(formWithError))),
      university => {
        for {
          _ <- univMatchDao.insert(university)
        } yield Redirect(routes.MatchController.univMatchIndex)
      }
    )
  }

  def updateUniversity(name: String) = Action.async{ implicit rs =>
    univMatchForm.bindFromRequest().fold(
      formWithError => univMatchDao.all().map(_ => BadRequest(views.html.editUnivMatchForm(name, formWithError))),
      university => {
        for {
          _ <- univMatchDao.update(name, university)
        } yield Redirect(routes.MatchController.univMatchIndex)
      }
    )
  }

  def deleteUniversity(name: String) = Action.async{ implicit  rs =>
    for {
      _ <- univMatchDao.delete(name)
    } yield Redirect(routes.MatchController.univMatchIndex)
  }
}
