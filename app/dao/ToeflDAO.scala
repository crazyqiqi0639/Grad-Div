package dao

import java.util.Date
import javax.inject.{ Inject, Singleton }

import model.Toefl
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.JdbcProfile
import scala.concurrent.{ ExecutionContext, Future }


class ToeflDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  private val ToeflReports = TableQuery[ToeflTable]

  def all(): Future[Seq[Toefl]] = db.run(ToeflReports.result)

  class ToeflTable(tag: Tag) extends Table[Toefl](tag, "toefl") {
    implicit val dateColumnType = MappedColumnType.base[Date, Long](d => d.getTime, d => new Date(d))

    def applicationNum = column[Long]("application_number")
    def date = column[Date]("date")
    def cbtEssay = column[Option[Int]]("cbt_essay")
    def cbtListening = column[Option[Int]]("cbt_listening")
    def cbtReading = column[Option[Int]]("cbt_reading")
    def cbtWriting = column[Option[Int]]("cbt_writing")
    def cbtTotal = column[Option[Int]]("cbt_total")
    def pbtWriting = column[Option[Int]]("pbt_writing")
    def pbtReading = column[Option[Int]]("pbt_reading")
    def pbtListening = column[Option[Int]]("pbt_listening")
    def pbtTotal = column[Option[Int]]("pbt_total")
    def ibtReading = column[Option[Int]]("ibt_reading")
    def ibtListening = column[Option[Int]]("ibt_listening")
    def ibtSpeaking = column[Option[Int]]("ibt_speaking")
    def ibtWriting = column[Option[Int]]("ibt_writing")
    def ibtTotal = column[Option[Int]]("ibt_total")

    def * = (
      applicationNum,date,
      cbtEssay, cbtListening, cbtReading, cbtWriting, cbtEssay,
      pbtWriting, pbtReading, pbtListening, pbtTotal,
      ibtReading, ibtListening, ibtSpeaking, ibtWriting, ibtTotal
    )<>(Toefl.tupled, Toefl.unapply)

  }

}
