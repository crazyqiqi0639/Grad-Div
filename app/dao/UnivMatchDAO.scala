package dao

import javax.inject.{ Inject, Singleton }

import model.UnivMatch
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.JdbcProfile
import scala.concurrent.{ ExecutionContext, Future }

@Singleton
class UnivMatchDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private val univMatches = TableQuery[UnivMatchTable]

  def all(): Future[Seq[UnivMatch]] = db.run(univMatches.result)

  def insert(univMatch: UnivMatch): Future[Unit] =
    db.run(univMatches += univMatch).map(_ => ())

  def delete(Name: String): Future[Unit] =
    db.run(univMatches.filter(_.shortName === Name).delete).map(_ => ())

  def update(Name: String, univMatch: UnivMatch): Future[Unit] = {
    val univMatchToUpdate: UnivMatch = univMatch.copy(Name)
    db.run(univMatches.filter(_.shortName === Name).update(univMatchToUpdate)).map(_ => ())
  }

  def findByName(Name: String): Future[Option[UnivMatch]] =
    db.run(univMatches.filter(_.shortName === Name).result.headOption)

  class UnivMatchTable(tag: Tag) extends Table[UnivMatch](tag, "univmatch") {

    def shortName = column[String]("shortname")
    def originalName = column[String]("name")

    def * = (shortName, originalName) <> (UnivMatch.tupled, UnivMatch.unapply)
  }
}