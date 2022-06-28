package controllers

import dao.SpecFactorDAO
import model.SpecFactor
import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ScoreController @Inject()
(
specFactorDao: SpecFactorDAO,
mcc: MessagesControllerComponents
) (implicit executionContext: ExecutionContext) extends MessagesAbstractController(mcc){

  val specFactorForm: Form[SpecFactor] = Form(
    mapping(
      "name" -> text,
      "factor" -> number
    )(SpecFactor.apply)(SpecFactor.unapply)
  )

  def index = Action.async{ implicit request =>
    specFactorDao.all().map{
      case specFactors => Ok(views.html.specFactorIndex(specFactorForm, specFactors))
    }
  }

  def create = Action.async{ implicit rs =>
    specFactorDao.all().map(_ => Ok(views.html.createSpecFactorForm(specFactorForm)))
  }

  def edit(name: String) = Action.async{ implicit rs =>
    val factors = for {
      factor <- specFactorDao.findByName(name)
    } yield factor

    factors.map{
      case factor =>
        factor match {
          case Some(s) => Ok(views.html.editSpecFactorForm(name, specFactorForm.fill(s)))
          case None => NotFound
        }
    }
  }

  def save = Action.async{ implicit rs =>
    specFactorForm.bindFromRequest().fold(
      formWithError => specFactorDao.all().map(_ => BadRequest(views.html.createSpecFactorForm(formWithError))),
      factor => {
        for {
          _ <- specFactorDao.insert(factor)
        } yield Redirect(routes.ScoreController.index)
      }
    )
  }

  def update(name: String) = Action.async{ implicit rs =>
    specFactorForm.bindFromRequest().fold(
      formWithError => specFactorDao.all().map(_ => BadRequest(views.html.editSpecFactorForm(name, formWithError))),
      university => {
        for {
          - <- specFactorDao.update(name, university)
        } yield Redirect(routes.ScoreController.index)
      }
    )
  }

  def delete(name: String) = Action.async{ implicit  rs =>
    for {
      _ <- specFactorDao.delete(name)
    } yield Redirect(routes.ScoreController.index)
  }
}

