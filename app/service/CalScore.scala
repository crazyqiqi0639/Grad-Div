package service

/**
** How to Score Study Experience:
1) Treat University Rank As a multiply factor. As URF
      Create a Table for this part.
2) Treat Qualification As a multiply factor. As QF
      Check it's bachelor or master or phd
3) Treat Specialisation As a multiply factor. As SF
      Check the relevance between Specialisation and Computer Science.
      !!! Text Similarity
4) Treat Class of Honor As Score.
5) Treat GPA/Best Score As Score.
6) Treat Rank of the Class Also as Score.
7) Different School has different scheme. Scheme will define how to score the GPA or Class of Honor.

The Formulation is:
 Final Result = URF * QF * SF * (Score)
  Score = Scheme(ClassOfHonor, GPA/BestScore, RankOfClass)

** How to Score Work Experience:

1) Treat Company As a Score
2) Treat Designation As a Factor
3) Treat Duration As a Factor
 **/

object CalScore{
  var UnivRankMap:Map[Int, Double] = Map()
  UnivRankMap += (1 -> 1.0)
  UnivRankMap += (2 -> 0.8)

  var QualificationMap:Map[String, Double] = Map()
  QualificationMap += ("BACHELOR'S DEGREE" -> 0.8)
  QualificationMap += ("MASTER'S DEGREE" -> 0.9)
  QualificationMap += ("DIPLOMA" -> 0.7)

  var CoHMap:Map[String, Int] = Map()
//  CoHMap += (None -> 0)
  CoHMap += ("1ST CLASS" -> 10)




  def calStudyExpScore(UnivRank: Double, QualificationFactor:Double, SpecFactor: Double,
                       Score: Int
                      ): Double = UnivRank * QualificationFactor * SpecFactor * Score

  def calWorkExpScore(Score: Int, DesignationFactor: Double, DurationFactor:Double): Double =
    DesignationFactor * DurationFactor * Score

}
