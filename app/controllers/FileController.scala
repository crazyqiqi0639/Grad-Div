package controllers

import dao.StudentDAO
import model.Student

import java.io._
import javax.inject.{Inject, Singleton}
import play.api.libs.Files
import play.api.libs.json.Json
import play.api.mvc.MultipartFormData.FilePart
import play.api.mvc.{AbstractController, ControllerComponents}
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import java.io.{File, FileInputStream}
import scala.util.control.Breaks.break
import scala.util.Random

/**
 * @author 梦境迷离
 * @version 1.0, 2019-03-23
 */
@Singleton
class FileController @Inject()(studentDao: StudentDAO,
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
      }
    }
    Ok(Json.obj("status" -> "OK"))
  }
}

