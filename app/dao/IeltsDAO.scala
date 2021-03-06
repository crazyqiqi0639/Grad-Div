package dao

import java.util.Date
import javax.inject.{ Inject, Singleton }

import model.Ielts
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.JdbcProfile
import scala.concurrent.{ ExecutionContext, Future }

@Singleton
class IeltsDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val IeltsReports = TableQuery[IeltsTable]

  def all(): Future[Seq[Ielts]] = db.run(IeltsReports.result)

  def insert(ielts: Ielts): Future[Unit] =
    db.run(IeltsReports += ielts).map(_ => ())

  def delete(AppNum: Long): Future[Unit] =
    db.run(IeltsReports.filter(_.applicationNum === AppNum).delete).map(_ =>())

  def update(AppNum: Long, ielts: Ielts): Future[Unit] = {
    val ieltsReportToUpdate: Ielts = ielts.copy(AppNum)
    db.run(IeltsReports.filter(_.applicationNum === AppNum).update(ieltsReportToUpdate)).map(_ => ())
  }

  def findByAppNum(AppNum: Long): Future[Option[Ielts]] =
    db.run(IeltsReports.filter(_.applicationNum === AppNum).result.headOption)

  class IeltsTable(tag: Tag) extends Table[Ielts](tag, "ielts") {
    implicit val dateColumnType = MappedColumnType.base[Date, Long](d => d.getTime, d => new Date(d))

    def applicationNum = column[Long]("application_number")
    def Date = column[Option[Long]]("date")
    def Overall = column[Option[Double]]("overall")
    def Listening = column[Option[Double]]("listening")
    def Reading = column[Option[Double]]("reading")
    def Writing = column[Option[Double]]("writing")
    def Speaking = column[Option[Double]]("speaking")


    def * = (applicationNum, Date, Overall, Listening,
    Reading, Writing, Speaking)<>(Ielts.tupled, Ielts.unapply)
  }
}
