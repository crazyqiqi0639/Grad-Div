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

  class IeltsTable(tag: Tag) extends Table[Ielts](tag, "ielts") {
    implicit val dateColumnType = MappedColumnType.base[Date, Long](d => d.getTime, d => new Date(d))

    def applicationNum = column[Long]("application_number")
    def Date = column[Date]("date")
    def Overall = column[Double]("overall")
    def Listening = column[Double]("listening")
    def Reading = column[Double]("reading")
    def Writing = column[Double]("writing")
    def Speaking = column[Double]("speaking")


    def * = (applicationNum, Date, Overall, Listening,
    Reading, Writing, Speaking)<>(Ielts.tupled, Ielts.unapply)
  }
}
