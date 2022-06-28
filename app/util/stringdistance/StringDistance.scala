package util.stringdistance


object StringDistance {
  def jaccard(p1: Seq[Any], p2: Seq[Any]): Double = {
    val anb = p1.intersect(p2)
    val aub = p1.concat(p2)
    val jaccardcoefficient = anb.length.toDouble / aub.length
    jaccardcoefficient
  }

  def editDist(s1:String, s2:String):Double ={
    val s1_length = s1.length+1
    val s2_length = s2.length+1
    val matrix=Array.ofDim[Int](s1_length,s2_length)
    for(i <- 0.until(s1_length)){
      matrix(i)(0) = i
    }
    for(j <- 0.until(s2_length)){
      matrix(0)(j) = j
    }
    var cost = 0
    for(j <- 1.until(s2_length)){
      for(i <- 1.until(s1_length)){
        if(s1.charAt(i-1)==s2.charAt(j-1)){
          cost = 0
        }else{
          cost = 1
        }
        matrix(i)(j)=math.min(math.min(matrix(i-1)(j)+1,matrix(i)(j-1)+1),matrix(i-1)(j-1)+cost)
      }
    }
    val ldist = matrix(s1_length-1)(s2_length-1)
    val lensum = s1.length + s2.length
    val ed = lensum - ldist
    val ratio = ed.toDouble / lensum.toDouble
    ratio
  }
}