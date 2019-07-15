package question

//Payattention that the order counts
object PredictTheWinner {

  def PredictTheWinner(nums: Array[Int]): Boolean = {

    val total = nums.sum

    def otherScore(score:Int) = total - score

    def isWinnerRecursive(start: Int, end: Int, currentScore: Int, total: Int, isFirst: Boolean): Boolean ={

      if(start == end) {
        val score = currentScore + nums(start)

      if(isFirst)
          return score >= otherScore(score)

      else
          return score > otherScore(score)

      }

      !isWinnerRecursive(start+1, end, total - currentScore, total + nums(start), !isFirst) || !isWinnerRecursive(start,  end - 1, total - currentScore, total + nums(end), isFirst)

    }

    isWinnerRecursive(0, nums.length-1, 0, 0, true)

  }

  def main(args: Array[String]): Unit = {

    assert(PredictTheWinner(Array(1,1)))

    assert(!PredictTheWinner(Array(1,5,2)))

    assert(PredictTheWinner(Array(1, 5, 233, 7)))

  }

}
