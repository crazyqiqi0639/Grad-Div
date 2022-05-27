package dao

import java.util.Date
import javax.inject.{ Inject, Singleton }

import model.Student
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.JdbcProfile
import scala.concurrent.{ ExecutionContext, Future }


@Singleton()
class StudentDAO  @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  private val Students = TableQuery[StudentTable]

  def all(): Future[Seq[Student]] = db.run(Students.result)

  def insert(student: Student) :Future[Unit] = db.run(Students += student).map{ _ => ()}

  def findByAppNum(AppNum: Long): Future[Option[Student]] =
    db.run(Students.filter(_.applicationNum === AppNum).result.headOption)

  def update(AppNum: Long, student: Student):Future[Unit]= {
    val studentToUpdate: Student = student.copy(AppNum)
    db.run(Students.filter(_.applicationNum === AppNum).update(studentToUpdate)).map(_ => ())
  }

  class StudentTable(tag: Tag) extends Table[Student](tag, "studentInfo") {

    def applicationNum = column[Long]("Application_Number")
    def Name = column[String]("NAME")
    def Gender = column[String]("Gender")

    def * = (applicationNum, Name, Gender)<>(Student.tupled, Student.unapply)
  }

}
