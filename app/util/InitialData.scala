package util

import java.text.SimpleDateFormat
import javax.inject.Inject

import dao.UniversityDAO
import model.University

import scala.concurrent.{ Await, ExecutionContext }
import scala.concurrent.duration.Duration
import scala.util.Try

class InitialData @Inject() (
                            universityDao: UniversityDAO
                            )(implicit executionContext: ExecutionContext) {
  def insert(): Unit = {
    val insertInitialDataFuture = for {
      count <- universityDao.count() if count == 0
      _ <- universityDao.insert(InitialData.university)
    } yield ()

    Try(Await.result(insertInitialDataFuture, Duration.Inf))
  }
  insert()

}

private object InitialData {
  def university = Seq(
    University("Tsinghua University","China","QS: 14", 1,1),
    University("National University of Singapore","Singapore","Qs: 11",1,1)
  )
}
