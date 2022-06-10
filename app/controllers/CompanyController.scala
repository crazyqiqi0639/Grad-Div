package controllers

import dao.CompanyDAO
import model.Company

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CompanyController @Inject()(
                                   companyDao: CompanyDAO,
                                   mcc: MessagesControllerComponents
                                 ) (implicit executionContext: ExecutionContext) extends MessagesAbstractController(mcc){

  val companyForm: Form[Company] = Form(
    mapping(
      "name" -> text(),
      "rank" -> number
    )(Company.apply)(Company.unapply)
  )

  def index = Action.async{ implicit rs =>
    companyDao.all().map(companies => Ok(views.html.companyIndex(companyForm, companies)))
  }

  def createCompany = Action.async{ implicit rs =>
    companyDao.all().map(_ => Ok(views.html.createCompanyForm(companyForm)))
  }

  def editCompany(name: String) = Action.async{ implicit rs =>
    val companies = for {
      company <- companyDao.findByName(name)
    } yield company

    companies.map {
      case Some(s) => Ok(views.html.editCompanyForm(name, companyForm.fill(s)))
      case None => NotFound
    }
  }

  def saveCompany = Action.async{ implicit rs =>
    companyForm.bindFromRequest().fold(
      formWithError => companyDao.all().map(_ => BadRequest(views.html.createCompanyForm(formWithError))),
      company => {
        for {
          _ <- companyDao.insert(company)
        } yield Redirect(routes.CompanyController.index)
      }
    )
  }

  def updateCompany(name: String) = Action.async{ implicit rs =>
    companyForm.bindFromRequest().fold(
      formWithError => companyDao.all().map(_ => BadRequest(views.html.editCompanyForm(name, formWithError))),
      company => {
        for {
          - <- companyDao.update(name, company)
        } yield Redirect(routes.CompanyController.index)
      }
    )
  }

  def deleteCompany(name: String) = Action.async{ implicit  rs =>
    for {
      _ <- companyDao.delete(name)
    } yield Redirect(routes.CompanyController.index)
  }
}
