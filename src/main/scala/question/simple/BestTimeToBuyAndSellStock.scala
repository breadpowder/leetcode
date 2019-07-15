package question.simple

object BestTimeToBuyAndSellStock {

  def maxProfit(prices: Array[Int]): Int = {

    var max = 0
    prices.length match

    {
      case 0 => 0
      case _ =>   var min = prices(0)

        for(p <- prices){

          if (p < min) min = p
          else max  = max max (p - min)

        }
    }


    return max
  }


  def maxProfitFoldLeft(prices: Array[Int]): Int = {

    var max = 0

    val result = prices.foldLeft[Tuple2[Int, Int]]((max, prices(0)))((start, p) =>
        if(p<start._2) (max, p)
        else (start._1 max (p - start._2), start._2)
    )

    return result._1
  }


    def main(args: Array[String]): Unit = {

    val test1 = Array(7,1,5,3,6,4)
    var result = maxProfitFoldLeft(test1)
    assert(result ==5, result)

    val test2 = Array(7,6,4,3,1)
    result = maxProfitFoldLeft(test2)
    assert(result ==0, result)


  }
}
