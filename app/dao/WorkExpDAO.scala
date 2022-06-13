package dao

import java.util.Date
import javax.inject.{ Inject, Singleton }

import model.WorkExp
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.JdbcProfile
import scala.concurrent.{ ExecutionContext, Future }

@Singleton
class WorkExpDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val workExps = TableQuery[WorkExpTable]

  def all(): Future[Seq[WorkExp]] = db.run(workExps.result)

  def insert(workExp: WorkExp): Future[Unit] =
    db.run(workExps += workExp).map(_ => ())

  def findByAppNum(AppNum: Long): Future[Option[WorkExp]] =
    db.run(workExps.filter(_.applicationNum === AppNum).result.headOption)

  def delete(AppNum: Long): Future[Unit] =
    db.run(workExps.filter(_.applicationNum === AppNum).delete).map(_ => ())

  def update(AppNum: Long, workExp: WorkExp): Future[Unit] = {
    val workToUpdate: WorkExp = workExp.copy(AppNum)
    db.run(workExps.filter(_.applicationNum === AppNum).update(workToUpdate)).map(_ => ())
  }


  class WorkExpTable(tag: Tag) extends Table[WorkExp](tag, "work") {
    implicit val dateColumnType = MappedColumnType.base[Date, Long](d => d.getTime, d => new Date(d))

    def applicationNum = column[Long]("application_number")
    def Name = column[Option[String]]("name")
    def Designation = column[Option[String]]("designation")
    def Date_From = column[Option[Long]]("date_from")
    def Date_To = column[Option[Long]]("date_to")
    def Duration = column[Option[String]]("duration")

    def * = (applicationNum, Name, Designation, Date_From, Date_To, Duration)<>(WorkExp.tupled, WorkExp.unapply)

  }

}
