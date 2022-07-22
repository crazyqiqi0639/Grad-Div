package controllers

import dao.{StudentDAO, StudyExpDAO}
import model.{Student, StudyExp, searchDemo}

import javax.inject._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.libs.json.Json

import scala.concurrent.{ExecutionContext, Future}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(
                                studentDao: StudentDAO,
                                studyExpDAO: StudyExpDAO,
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



  val searchDemoForm: Form[searchDemo] = Form(
    mapping(
      "name" -> default(text(), "")
    )(searchDemo.apply)(searchDemo.unapply)
  )

  val studyExpForm: Form[StudyExp] = Form(
    mapping(
            "application_number" -> longNumber,
            "name" -> optional(text()),
            "location" -> optional(text()),
            "qualification" -> optional(text()),
            "specialisation" -> optional(text()),
            "class_of_honor" -> optional(text()),
            "end_date" -> optional(longNumber),
            "expect_complete_date" -> optional(longNumber),
            "best_score" -> optional(of(doubleFormat)),
            "gpa" -> optional(of(doubleFormat)),
            "rank" -> optional(text()),
            "subsidy" -> optional(text()),
            "name_of_college" -> optional(text()),
            "qualification_type" -> optional(text())
    )(StudyExp.apply)(StudyExp.unapply)
  )

  def studentIndex: Action[AnyContent]= Action.async { implicit  request =>
    studentDao.all().map(students => Ok(views.html.studentIndex(students)))
  }

  implicit val StudentFormat = Json.format[Student]
  implicit val StudyExpFormat = Json.format[StudyExp]
  implicit val StudentInfoFormat = Json.format[(Student, StudyExp)]

  def getAll: Action[AnyContent] = Action.async { implicit request =>
    studentDao.all().map(students => Ok(Json.toJson(students)))
  }

  def get(id: Long): Action[AnyContent] = Action.async{ implicit request =>
    studentDao.findByAppNum(id).map(student => Ok(Json.toJson(student)))
  }

  def updateStudent(AppNum: Long): Action[AnyContent] = Action.async { implicit request =>
    studentForm.bindFromRequest().fold(
      formWithErrors => {
        formWithErrors.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      student => {
        for {
          _ <- studentDao.update(AppNum, student)
        } yield Redirect(routes.HomeController.studentIndex)
      }
    )
  }

  def studentSearchIndex: Action[AnyContent]= Action.async { implicit  request =>
    studentDao.list().map(students => Ok(views.html.searchDemo(students, searchDemoForm)))
  }

  def searchStudent: Action[AnyContent] = Action.async { implicit request =>
    searchDemoForm.bindFromRequest().fold(
      formWithErrors => studentDao.list().map(students => BadRequest(views.html.searchDemo(students, formWithErrors))),
      name => {
        for {
          students <- studentDao.findByName(name.Name)
        } yield Ok(Json.toJson(students))
//        } yield Ok(views.html.searchDemo(students, searchDemoForm))
      }
    )
  }

  def index: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def createStudent: Action[AnyContent] = Action.async { implicit request =>
    studentDao.all().map(_ => Ok(views.html.createStudentForm(studentForm)))

  }

  def insertStudent(): Action[AnyContent] = Action.async { implicit request =>
    val student: Student = studentForm.bindFromRequest().get
    studentDao.insert(student).map(_ => Redirect(routes.HomeController.studentIndex))
  }

  def editStudent(AppNum: Long): Action[AnyContent] = Action.async { implicit request =>
    val studentInfo = for {
      student <- studentDao.findByAppNum(AppNum)
      studyExp <- studyExpDAO.findAllByAppNum(AppNum)
    } yield (student, studyExp)

    studentInfo.map {
      case (student, studyExp) =>
        student match {
          case Some(s) => Ok(views.html.editStudentForm(AppNum, studentForm.fill(s), studyExp))
          case None => NotFound
        }
    }
  }

  def deleteStudent(AppNum: Long): Action[AnyContent] = Action {
    studentDao.delete(AppNum)
    Ok(Json.obj(
      "status" -> 200
    ))
    Redirect(routes.HomeController.studentIndex)
  }

  def saveStudent:Action[AnyContent] = Action.async{ implicit request =>
    studentForm.bindFromRequest().fold(
      formWithErrors => studentDao.all().map(_ => BadRequest(views.html.createStudentForm(formWithErrors))),
      student => {
        for {
          _ <- studentDao.insert(student)
        } yield Redirect(routes.HomeController.studentIndex)
      }
    )
  }

}
