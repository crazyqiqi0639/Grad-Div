package dao

import scala.concurrent.{ ExecutionContext, Future }
import javax.inject.Inject

import model.Cat
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile

class CatDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val Cats = TableQuery[CatsTable]

  def all(): Future[Seq[Cat]] = db.run(Cats.result)

  def insert(cat: Cat): Future[Unit] = db.run(Cats += cat).map { _ => () }

  private class CatsTable(tag: Tag) extends Table[Cat](tag, "cat") {

    def name = column[String]("name", O.PrimaryKey)
    def color = column[String]("color")

    def * = (name, color) <> (Cat.tupled, Cat.unapply)

//    def * = (name, color) <> (Cat.tupled, Cat.unapply)
  }
}