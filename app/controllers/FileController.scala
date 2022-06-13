package controllers

import dao.{IeltsDAO, StudentDAO, StudyExpDAO, ToeflDAO, WorkExpDAO}
import model.{Student, StudyExp, WorkExp}

import java.io._
import javax.inject.{Inject, Singleton}
import play.api.libs.Files
import play.api.libs.json.Json
import play.api.mvc.MultipartFormData.FilePart
import play.api.mvc.{AbstractController, ControllerComponents}
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import java.io.{File, FileInputStream}
import java.text.SimpleDateFormat
import scala.util.control.Breaks.break
import scala.util.Random

/**
 * @author 梦境迷离
 * @version 1.0, 2019-03-23
 */
@Singleton
class FileController @Inject()(studentDao: StudentDAO,
                               workExpDao: WorkExpDAO,
                               studyExpDao: StudyExpDAO,
                               ieltsDao: IeltsDAO,
                               toeflDAO: ToeflDAO,
                               cc: ControllerComponents) extends AbstractController(cc) {


  /**
   * 显示上传页面
   *
   * @return
   */
  def toUpload = Action {
    Ok(views.html.fileupload("File Upload"))
  }


  /**
   * 使用ajax的文件上传
   *
   * @return
   */
  def ajaxUpload = Action(parse.multipartFormData) {
    implicit request => {
      request.body.file("file").map(file => uploadFile(file)).getOrElse(Ok(Json.obj("status" -> "FAIL")))
    }

  }

  /**
   * 文件上传
   *
   * @return
   */
  def upload = Action(parse.multipartFormData) {
    implicit request => {
      request.body.file("file").map(file => uploadFile(file)).getOrElse(Ok(Json.obj("status" -> "FAIL")))
    }
  }

  private val uploadFile = (file: FilePart[Files.TemporaryFile]) => {
    val sdf = new SimpleDateFormat("yyyy/MM/dd")
    val fileName = file.filename
    println(s"File Name:$fileName")
    var nameSuffix = ""
    //检测文件是否存在后缀名
    if (fileName.lastIndexOf(".") > -1) {
      nameSuffix = fileName.substring(fileName.lastIndexOf("."))
      println(s"Suffix:$nameSuffix")

    }
    //给文件取新的随机名
    val newName = new Random().nextInt(1000000000)
    //目标目录路径
    val toFile: File = new File("Resource/uploadFiles/")
    if (!toFile.exists()) {
      //不存在就创建新的文件夹
      toFile.mkdir()
    }
    //准备存放的文件
    val f = new File(s"Resource/uploadFiles/$newName$nameSuffix")
    //将文件移动到新的文件
    file.ref.moveTo(f, true)
//    val filePath = new File("Resource/GDA-Data-Sample.xlsx")
    val fs = new FileInputStream(f)
    val xssfWorkbook: XSSFWorkbook = new XSSFWorkbook(fs)
    for(i <- 0 until xssfWorkbook.getNumberOfSheets){
      //获取表格每一个sheet
      val xssfSheet = xssfWorkbook.getSheetAt(i)
      val titleRow = xssfSheet.getRow(0)
      for(row <- 1 to xssfSheet.getLastRowNum()){
        //获取表格每一行
        val xssfRow = xssfSheet.getRow(row)
        val AppNum = xssfRow.getCell(0).getNumericCellValue.toInt
        val StuName = xssfRow.getCell(1).toString
        val StuGender = xssfRow.getCell(2).toString
        val student = Student(AppNum, StuName, StuGender)
        studentDao.insert(student)

        /*
        This part is for work experience
         */
        val NameOfCompany_1: Option[String] = Option {
          val name = xssfRow.getCell(3).getRawValue
          if (name != null) {
            xssfRow.getCell(3).toString
          } else {
            "Not Provided"
          }
        }
        val Designation_1: Option[String] = Option {
          val designation = xssfRow.getCell(4).getRawValue
          if (designation != null) {
            xssfRow.getCell(4).toString
          } else {
            "Not Provided"
          }
        }
        val DateEmployedFrom_1: Option[Long] = Option {
          var raw = xssfRow.getCell(5).getRawValue
          if (raw != null) {
            val value = raw.toDouble
            val date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)
            val time = sdf.format(date)
            sdf.parse(time).getTime
          } else {
            0
          }
        }
        val DateEmployedTo_1: Option[Long] = Option {
          val raw = xssfRow.getCell(6).getRawValue
          if (raw != null) {
            val value = raw.toDouble
            val date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)
            val time = sdf.format(date)
            sdf.parse(time).getTime
          } else {
            0
          }
        }
        val Duration_1: Option[String] = Option {
          val duration = xssfRow.getCell(7).getRawValue
          if (duration != null) {
            xssfRow.getCell(7).toString
          } else {
            "Not Provided"
          }
        }
        if (!Designation_1.get.equals("Not Provided")){
          val workExperience_1 = WorkExp(ApplicationNum = AppNum, Name = NameOfCompany_1, Designation = Designation_1, Date_From = DateEmployedFrom_1,
            Date_To = DateEmployedTo_1, Duration = Duration_1)
          workExpDao.insert(workExperience_1)
        }

        val NameOfCompany_2: Option[String] = Option {
          val name = xssfRow.getCell(8).getRawValue
          if (name != null) {
            xssfRow.getCell(8).toString
          } else {
            "Not Provided"
          }
        }
        val Designation_2: Option[String] = Option {
          val designation = xssfRow.getCell(9).getRawValue
          if (designation != null) {
            xssfRow.getCell(9).toString
          } else {
            "Not Provided"
          }
        }
        val DateEmployedFrom_2: Option[Long] = Option {
          var raw = xssfRow.getCell(10).getRawValue
          if (raw != null) {
            val value = raw.toDouble
            val date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)
            val time = sdf.format(date)
            sdf.parse(time).getTime
          } else {
            0
          }
        }
        val DateEmployedTo_2: Option[Long] = Option {
          val raw = xssfRow.getCell(11).getRawValue
          if (raw != null) {
            val value = raw.toDouble
            val date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)
            val time = sdf.format(date)
            sdf.parse(time).getTime
          } else {
            0
          }
        }
        val Duration_2: Option[String] = Option {
          val duration = xssfRow.getCell(12).getRawValue
          if (duration != null) {
            xssfRow.getCell(12).toString
          } else {
            "Not Provided"
          }
        }
        if (!Designation_2.get.equals("Not Provided")) {
          val workExperience_2 = WorkExp(ApplicationNum = AppNum, Name = NameOfCompany_2, Designation = Designation_2, Date_From = DateEmployedFrom_2,
            Date_To = DateEmployedTo_2, Duration = Duration_2)
          workExpDao.insert(workExperience_2)
        }

        val NameOfCompany_3: Option[String] = Option {
          val name = xssfRow.getCell(13).getRawValue
          if (name != null) {
            xssfRow.getCell(13).toString
          } else {
            "Not Provided"
          }
        }
        val Designation_3: Option[String] = Option {
          val designation = xssfRow.getCell(14).getRawValue
          if (designation != null) {
            xssfRow.getCell(14).toString
          } else {
            "Not Provided"
          }
        }
        val DateEmployedFrom_3: Option[Long] = Option {
          var raw = xssfRow.getCell(15).getRawValue
          if (raw != null) {
            val value = raw.toDouble
            val date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)
            val time = sdf.format(date)
            sdf.parse(time).getTime
          } else {
            0
          }
        }
        val DateEmployedTo_3: Option[Long] = Option {
          val raw = xssfRow.getCell(16).getRawValue
          if (raw != null) {
            val value = raw.toDouble
            val date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)
            val time = sdf.format(date)
            sdf.parse(time).getTime
          } else {
            0
          }
        }
        val Duration_3: Option[String] = Option {
          val duration = xssfRow.getCell(17).getRawValue
          if (duration != null) {
            xssfRow.getCell(17).toString
          } else {
            "Not Provided"
          }
        }
        if (!Designation_3.get.equals("Not Provided")) {
          val workExperience_3 = WorkExp(ApplicationNum = AppNum, Name = NameOfCompany_3, Designation = Designation_3, Date_From = DateEmployedFrom_3,
            Date_To = DateEmployedTo_3, Duration = Duration_3)
          workExpDao.insert(workExperience_3)
        }

        val NameOfCompany_4: Option[String] = Option {
          val name = xssfRow.getCell(18).getRawValue
          if (name != null) {
            xssfRow.getCell(18).toString
          } else {
            "Not Provided"
          }
        }
        val Designation_4: Option[String] = Option {
          val designation = xssfRow.getCell(19).getRawValue
          if (designation != null) {
            xssfRow.getCell(19).toString
          } else {
            "Not Provided"
          }
        }
        val DateEmployedFrom_4: Option[Long] = Option {
          var raw = xssfRow.getCell(20).getRawValue
          if (raw != null) {
            val value = raw.toDouble
            val date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)
            val time = sdf.format(date)
            sdf.parse(time).getTime
          } else {
            0
          }
        }
        val DateEmployedTo_4: Option[Long] = Option {
          val raw = xssfRow.getCell(21).getRawValue
          if (raw != null) {
            val value = raw.toDouble
            val date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)
            val time = sdf.format(date)
            sdf.parse(time).getTime
          } else {
            0
          }
        }
        val Duration_4: Option[String] = Option {
          val duration = xssfRow.getCell(22).getRawValue
          if (duration != null) {
            xssfRow.getCell(22).toString
          } else {
            "Not Provided"
          }
        }
        if (!Designation_4.get.equals("Not Provided")) {
          val workExperience_4 = WorkExp(ApplicationNum = AppNum, Name = NameOfCompany_4, Designation = Designation_4, Date_From = DateEmployedFrom_4,
            Date_To = DateEmployedTo_4, Duration = Duration_4)
          workExpDao.insert(workExperience_4)
        }

        /*
        This part is for Study Experience
         */
        val NameOfUniversity_1: Option[String] = Option {
          val duration = xssfRow.getCell(23).getRawValue
          if (duration != null) {
            xssfRow.getCell(23).toString
          } else {
            "Not Provided"
          }
        }

        val Location_1: Option[String] = Option {
          val duration = xssfRow.getCell(24).getRawValue
          if (duration != null) {
            xssfRow.getCell(24).toString
          } else {
            "Not Provided"
          }
        }

        val Qualification_1: Option[String] = Option {
          val duration = xssfRow.getCell(25).getRawValue
          if (duration != null) {
            xssfRow.getCell(25).toString
          } else {
            "Not Provided"
          }
        }

        val Specialisation_1: Option[String] = Option {
          val duration = xssfRow.getCell(26).getRawValue
          if (duration != null) {
            xssfRow.getCell(26).toString
          } else {
            "Not Provided"
          }
        }

        val ClassOfHonor_1: Option[String] = Option {
          val duration = xssfRow.getCell(27).getRawValue
          if (duration != null) {
            xssfRow.getCell(27).toString
          } else {
            "Not Provided"
          }
        }

        val CourseEndDate_1: Option[Long] = Option {
          val raw = xssfRow.getCell(28).getRawValue
          if (raw != null) {
            val value = raw.toDouble
            val date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)
            val time = sdf.format(date)
            sdf.parse(time).getTime
          } else {
            0
          }
        }

        val ExpectEndDate_1: Option[Long] = Option {
          val raw = xssfRow.getCell(29).getRawValue
          if (raw != null) {
            val value = raw.toDouble
            val date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)
            val time = sdf.format(date)
            sdf.parse(time).getTime
          } else {
            0
          }
        }

        val BestScore_1: Option[Double] = Option {
          val raw = xssfRow.getCell(30).getRawValue
          if (raw != null) {
            raw.toDouble
          } else {
            0.0
          }
        }

        val GPA_1: Option[Double] = Option {
          val raw = xssfRow.getCell(31).getRawValue
          if (raw != null) {
            raw.toDouble
          } else {
            0.0
          }
        }

        val Rank_1: Option[String] = Option {
          val duration = xssfRow.getCell(32).getRawValue
          if (duration != null) {
            xssfRow.getCell(32).toString
          } else {
            "Not Provided"
          }
        }

        val Subsidy_1: Option[String] = Option {
          val duration = xssfRow.getCell(33).getRawValue
          if (duration != null) {
            xssfRow.getCell(33).toString
          } else {
            "Not Provided"
          }
        }

        val NameofCollege_1: Option[String] = Option {
          val duration = xssfRow.getCell(34).getRawValue
          if (duration != null) {
            xssfRow.getCell(34).toString
          } else {
            "Not Provided"
          }
        }

        val QualificationType_1: Option[String] = Option {
          val duration = xssfRow.getCell(35).getRawValue
          if (duration != null) {
            xssfRow.getCell(35).toString
          } else {
            "Not Provided"
          }
        }

        if (!NameOfUniversity_1.get.equals("Not Provided")){
          val studyExperience_1 = StudyExp(
            ApplicationNum = AppNum,
            Name = NameOfUniversity_1,
            Location = Location_1,
            Qualification = Qualification_1,
            Specialisation = Specialisation_1,
            ClassOfHonor = ClassOfHonor_1,
            EndDate = CourseEndDate_1,
            ExpectCompleteDate = ExpectEndDate_1,
            BestScore = BestScore_1,
            Gpa = GPA_1,
            Rank = Rank_1,
            Subsidy = Subsidy_1,
            NameOfCollege = NameofCollege_1,
            QualificationType = QualificationType_1
          )
          studyExpDao.insert(studyExperience_1)
        }

        val NameOfUniversity_2: Option[String] = Option {
          val duration = xssfRow.getCell(36).getRawValue
          if (duration != null) {
            xssfRow.getCell(36).toString
          } else {
            "Not Provided"
          }
        }

        val Location_2: Option[String] = Option {
          val duration = xssfRow.getCell(37).getRawValue
          if (duration != null) {
            xssfRow.getCell(37).toString
          } else {
            "Not Provided"
          }
        }

        val Qualification_2: Option[String] = Option {
          val duration = xssfRow.getCell(38).getRawValue
          if (duration != null) {
            xssfRow.getCell(38).toString
          } else {
            "Not Provided"
          }
        }

        val Specialisation_2: Option[String] = Option {
          val duration = xssfRow.getCell(39).getRawValue
          if (duration != null) {
            xssfRow.getCell(39).toString
          } else {
            "Not Provided"
          }
        }

        val ClassOfHonor_2: Option[String] = Option {
          val duration = xssfRow.getCell(40).getRawValue
          if (duration != null) {
            xssfRow.getCell(40).toString
          } else {
            "Not Provided"
          }
        }

        val CourseEndDate_2: Option[Long] = Option {
          val raw = xssfRow.getCell(41).getRawValue
          if (raw != null) {
            val value = raw.toDouble
            val date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)
            val time = sdf.format(date)
            sdf.parse(time).getTime
          } else {
            0
          }
        }

        val ExpectEndDate_2: Option[Long] = Option {
          val raw = xssfRow.getCell(42).getRawValue
          if (raw != null) {
            val value = raw.toDouble
            val date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)
            val time = sdf.format(date)
            sdf.parse(time).getTime
          } else {
            0
          }
        }

        val BestScore_2: Option[Double] = Option {
          val raw = xssfRow.getCell(43).getRawValue
          if (raw != null) {
            raw.toDouble
          } else {
            0.0
          }
        }

        val GPA_2: Option[Double] = Option {
          val raw = xssfRow.getCell(44).getRawValue
          if (raw != null) {
            raw.toDouble
          } else {
            0.0
          }
        }

        val Rank_2: Option[String] = Option {
          val duration = xssfRow.getCell(45).getRawValue
          if (duration != null) {
            xssfRow.getCell(45).toString
          } else {
            "Not Provided"
          }
        }

        val Subsidy_2: Option[String] = Option {
          val duration = xssfRow.getCell(46).getRawValue
          if (duration != null) {
            xssfRow.getCell(46).toString
          } else {
            "Not Provided"
          }
        }

        val NameofCollege_2: Option[String] = Option {
          val duration = xssfRow.getCell(47).getRawValue
          if (duration != null) {
            xssfRow.getCell(47).toString
          } else {
            "Not Provided"
          }
        }

        val QualificationType_2: Option[String] = Option {
          val duration = xssfRow.getCell(48).getRawValue
          if (duration != null) {
            xssfRow.getCell(48).toString
          } else {
            "Not Provided"
          }
        }

        if (!NameOfUniversity_2.get.equals("Not Provided")){
          val studyExperience_2 = StudyExp(
            ApplicationNum = AppNum,
            Name = NameOfUniversity_2,
            Location = Location_2,
            Qualification = Qualification_2,
            Specialisation = Specialisation_2,
            ClassOfHonor = ClassOfHonor_2,
            EndDate = CourseEndDate_2,
            ExpectCompleteDate = ExpectEndDate_2,
            BestScore = BestScore_2,
            Gpa = GPA_2,
            Rank = Rank_2,
            Subsidy = Subsidy_2,
            NameOfCollege = NameofCollege_2,
            QualificationType = QualificationType_2
          )
          studyExpDao.insert(studyExperience_2)
        }

        val NameOfUniversity_3: Option[String] = Option {
          val duration = xssfRow.getCell(49).getRawValue
          if (duration != null) {
            xssfRow.getCell(49).toString
          } else {
            "Not Provided"
          }
        }

        val Location_3: Option[String] = Option {
          val duration = xssfRow.getCell(50).getRawValue
          if (duration != null) {
            xssfRow.getCell(50).toString
          } else {
            "Not Provided"
          }
        }

        val Qualification_3: Option[String] = Option {
          val duration = xssfRow.getCell(51).getRawValue
          if (duration != null) {
            xssfRow.getCell(51).toString
          } else {
            "Not Provided"
          }
        }

        val Specialisation_3: Option[String] = Option {
          val duration = xssfRow.getCell(52).getRawValue
          if (duration != null) {
            xssfRow.getCell(52).toString
          } else {
            "Not Provided"
          }
        }

        val ClassOfHonor_3: Option[String] = Option {
          val duration = xssfRow.getCell(53).getRawValue
          if (duration != null) {
            xssfRow.getCell(53).toString
          } else {
            "Not Provided"
          }
        }

        val CourseEndDate_3: Option[Long] = Option {
          val raw = xssfRow.getCell(54).getRawValue
          if (raw != null) {
            val value = raw.toDouble
            val date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)
            val time = sdf.format(date)
            sdf.parse(time).getTime
          } else {
            0
          }
        }

        val ExpectEndDate_3: Option[Long] = Option {
          val raw = xssfRow.getCell(55).getRawValue
          if (raw != null) {
            val value = raw.toDouble
            val date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)
            val time = sdf.format(date)
            sdf.parse(time).getTime
          } else {
            0
          }
        }

        val BestScore_3: Option[Double] = Option {
          val raw = xssfRow.getCell(56).getRawValue
          if (raw != null) {
            raw.toDouble
          } else {
            0.0
          }
        }

        val GPA_3: Option[Double] = Option {
          val raw = xssfRow.getCell(57).getRawValue
          if (raw != null) {
            raw.toDouble
          } else {
            0.0
          }
        }

        val Rank_3: Option[String] = Option {
          val duration = xssfRow.getCell(58).getRawValue
          if (duration != null) {
            xssfRow.getCell(58).toString
          } else {
            "Not Provided"
          }
        }

        val Subsidy_3: Option[String] = Option {
          val duration = xssfRow.getCell(59).getRawValue
          if (duration != null) {
            xssfRow.getCell(59).toString
          } else {
            "Not Provided"
          }
        }

        val NameofCollege_3: Option[String] = Option {
          val duration = xssfRow.getCell(60).getRawValue
          if (duration != null) {
            xssfRow.getCell(60).toString
          } else {
            "Not Provided"
          }
        }

        val QualificationType_3: Option[String] = Option {
          val duration = xssfRow.getCell(61).getRawValue
          if (duration != null) {
            xssfRow.getCell(61).toString
          } else {
            "Not Provided"
          }
        }

        if (!NameOfUniversity_3.get.equals("Not Provided")){
          val studyExperience_3 = StudyExp(
            ApplicationNum = AppNum,
            Name = NameOfUniversity_3,
            Location = Location_3,
            Qualification = Qualification_3,
            Specialisation = Specialisation_3,
            ClassOfHonor = ClassOfHonor_3,
            EndDate = CourseEndDate_3,
            ExpectCompleteDate = ExpectEndDate_3,
            BestScore = BestScore_3,
            Gpa = GPA_3,
            Rank = Rank_3,
            Subsidy = Subsidy_3,
            NameOfCollege = NameofCollege_3,
            QualificationType = QualificationType_3
          )
          studyExpDao.insert(studyExperience_3)
        }

      }
    }
    Ok(Json.obj("status" -> "OK"))
  }
}

