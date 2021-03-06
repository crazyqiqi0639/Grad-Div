package dao

import model.University
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}
import javax.inject.{Inject, Singleton}

@Singleton()
class UniversityDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  private val Universities = TableQuery[UniversityTable]

  def all(): Future[Seq[University]] = db.run(Universities.result)

  def insert(university: University): Future[Unit] =
    db.run(Universities += university).map(_ => ())

  def insert(universities: Seq[University]): Future[Unit] =
    db.run(this.Universities ++= universities).map(_ => ())

  def count(): Future[Int] = {
    // this should be changed to
    // db.run(computers.length.result)
    // when https://github.com/slick/slick/issues/1237 is fixed
    db.run(Universities.map(_.Name).length.result)
  }

  def delete(name: String): Future[Unit] =
    db.run(Universities.filter(_.Name === name).delete).map(_ => ())

  def update(name: String, university: University): Future[Unit] = {
    val universityToUpdate: University = university.copy(name)
    db.run(Universities.filter(_.Name === name).update(universityToUpdate)).map(_ => ())
  }

  def findByName(name: String): Future[Option[University]] =
    db.run(Universities.filter(_.Name === name).result.headOption)

  class UniversityTable(tag: Tag) extends Table[University](tag, "university") {
    def Name = column[String]("name")
    def Country= column[String]("country")
    def Other = column[String]("other")
    def Scheme = column[Int]("scheme")
    def Rank = column[Int]("rank")

    def * =
      (Name,Country,Other, Scheme, Rank)<>(University.tupled, University.unapply)
  }

}
