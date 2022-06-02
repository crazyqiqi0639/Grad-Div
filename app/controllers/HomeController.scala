package controllers

import dao.StudentDAO
import model.Student

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._

import scala.concurrent.ExecutionContext

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(
                                studentDao: StudentDAO,
                                mcc: MessagesControllerComponents
                              ) (implicit executionContext: ExecutionContext) extends MessagesAbstractController(mcc){

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */

    val studentForm: Form[Student] = Form(
      mapping(
        "application_number" -> longNumber,
        "name" -> text(),
        "gender" -> text()
      )(Student.apply)(Student.unapply)
    )

  def studentIndex= Action.async { implicit  request =>
    studentDao.all().map {
      case (students) => Ok(views.html.studentIndex(studentForm, students))
    }
  }

  def updateStudent(AppNum: Long) = Action.async { implicit request =>
    studentForm.bindFromRequest().fold(
      formWithErrors => studentDao.all().map(_ => BadRequest(views.html.editStudentForm(AppNum, formWithErrors))),
      student => {
        for {
          _ <- studentDao.update(AppNum, student)
        } yield Redirect(routes.HomeController.studentIndex)
      }
    )
  }

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok{
      request.flash.get("success").getOrElse("Welcome Stranger!")
    }
  }

  def insertStudent = Action.async { implicit request =>
    val student: Student = studentForm.bindFromRequest().get
    studentDao.insert(student).map(_ => Redirect(routes.HomeController.studentIndex))
  }

  def editStudent(AppNum: Long) = Action.async{ implicit request =>
    val students = for {
      student <- studentDao.findByAppNum(AppNum)
    } yield student

    students.map{
      case student =>
        student match {
          case Some(s) => Ok(views.html.editStudentForm(AppNum, studentForm.fill(s)))
          case None => NotFound
        }
      }
    }

  def deleteStudent(AppNum: Long) = Action.async { implicit request =>
    for {
      _ <- studentDao.delete(AppNum)
    } yield Redirect(routes.HomeController.studentIndex)

  }

}
