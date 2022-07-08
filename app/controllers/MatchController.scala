package controllers

import dao.{CompanyMatchDAO, UnivMatchDAO}
import model.{CompanyMatch, UnivMatch}

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class MatchController @Inject()(
                                 univMatchDao: UnivMatchDAO,
                                 companyMatchDAO: CompanyMatchDAO,
                                 mcc: MessagesControllerComponents
                               ) (implicit executionContext: ExecutionContext) extends MessagesAbstractController(mcc){

  def index = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.matchIndex())
  }

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


  val compMatchForm: Form[CompanyMatch] = Form(
    mapping(
      "shortname" -> text(),
      "name" -> text()
    )(CompanyMatch.apply)(CompanyMatch.unapply)
  )

  def compMatchIndex = Action.async{ implicit rs =>
    companyMatchDAO.all().map(companies => Ok(views.html.compMatchIndex(compMatchForm, companies)))
  }

  def createCompany = Action.async{ implicit rs =>
    companyMatchDAO.all().map(_ => Ok(views.html.createCompMatchForm(compMatchForm)))
  }

  def editCompany(name: String) = Action.async{ implicit rs =>
    val companies = for {
      company <- companyMatchDAO.findByName(name)
    } yield company

    companies.map {
      case Some(s) => Ok(views.html.editCompMatchForm(name, compMatchForm.fill(s)))
      case None => NotFound
    }
  }

  def saveCompany = Action.async{ implicit rs =>
    compMatchForm.bindFromRequest().fold(
      formWithError => companyMatchDAO.all().map(_ => BadRequest(views.html.createCompMatchForm(formWithError))),
      company => {
        for {
          _ <- companyMatchDAO.insert(company)
        } yield Redirect(routes.MatchController.compMatchIndex)
      }
    )
  }

  def updateCompany(name: String) = Action.async{ implicit rs =>
    compMatchForm.bindFromRequest().fold(
      formWithError => companyMatchDAO.all().map(_ => BadRequest(views.html.editCompMatchForm(name, formWithError))),
      company => {
        for {
          - <- companyMatchDAO.update(name, company)
        } yield Redirect(routes.MatchController.compMatchIndex)
      }
    )
  }

  def deleteCompany(name: String) = Action.async{ implicit  rs =>
    for {
      _ <- companyMatchDAO.delete(name)
    } yield Redirect(routes.MatchController.compMatchIndex)
  }
}
