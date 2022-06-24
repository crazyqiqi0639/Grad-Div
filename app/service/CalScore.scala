package service

import javax.inject.Inject

import dao.UniversityDAO
import model.{University, WorkExp, StudyExp}

import scala.concurrent.{ Await, ExecutionContext }
import scala.concurrent.duration.Duration
import scala.util.Try

object CalScore{

  def calStudyExpScore(studyExp: StudyExp): Int = ???

  def calWorkExpScore(workExp: WorkExp): Int = ???

}
