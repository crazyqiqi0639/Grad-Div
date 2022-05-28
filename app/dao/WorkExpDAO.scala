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


  class WorkExpTable(tag: Tag) extends Table[WorkExp](tag, "work") {
    implicit val dateColumnType = MappedColumnType.base[Date, Long](d => d.getTime, d => new Date(d))

    def applicationNum = column[Long]("application_number")
    def Name = column[String]("name")
    def Designation = column[String]("designation")
    def Date_From = column[Date]("date_from")
    def Date_To = column[Date]("date_to")
    def Duration = column[String]("duration")

    def * = (applicationNum, Name, Designation, Date_From, Date_To, Duration)<>(WorkExp.tupled, WorkExp.unapply)

  }

}
