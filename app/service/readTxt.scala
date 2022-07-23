package service

import java.io.File

object readTxt extends App{
  def readFromTxtByLine(filePath:String) = {
    //导入Scala的IO包
    import scala.io.Source
    val filePaths = new File(filePath)
    //以指定的UTF-8字符集读取文件，第一个参数可以是字符串或者是java.io.File
    val source = Source.fromFile(filePaths, "UTF-8")
    //或取文件中所有行
    val lineIterator = source.getLines()

    lineIterator.foreach(
      line => {
        val lineArray = line.trim.split(":")
        println(lineArray.length)
      }
    )
    //迭代打印所有行
//    lineIterator.foreach(
//      line => println(line)
//    )
    //将所有行放到数组中
    val lines = source.getLines().toArray
    source.close()
    //println(lines.size)
    lines
  }
//  val lines = readFromTxtByLine("Resource/univ_rank_3.txt")
//  for (i <- 0 until lines.length) {
//    val lineArray = lines(i).trim.split("@")
//    val country = lineArray.apply(0)
//    val rank = lineArray.apply(1)
//    if (lineArray.length.equals(3)) {
//      val name = lineArray.apply(2)
//      println("no nick name , name: "+ name)
//    } else {
//      val shortname = lineArray.apply(2)
//      val name = lineArray.apply(3)
//      println("short name: "+ shortname+  " name: " + name)
//    }
//    }
    val lines = readFromTxtByLine("Resource/sum_country.txt")
//    for (i <- 0 until lines.length) {
//      val lineArray = lines(i).trim.split(":")
//      println(lineArray.length)
//      }



  }
