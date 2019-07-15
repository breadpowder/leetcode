package question.dp

/*

309 BestTimeToBuyStockWithCoolDown

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]

 */
object BestTimeToBuyStockWithCoolDown {

  def maxProfit(prices: Array[Int]): Int = {


    if(prices.isEmpty) return 0

    var previous = Array(0, -prices(0), Integer.MIN_VALUE)
// TODO eror here, assign array ref does not help copy the array   var next = new Array[Int](3)


    for(i <- 1 to prices.length-1) {

          //println("Max value buy, cool, sell " + previous.mkString(","))

          val coolMax = previous(0) max previous(2)
          val holdMax = previous(1) max (previous(0) - prices(i))
          val afterSell= previous(1) + prices(i)

          //you have to copy array
          previous(0) =coolMax
          previous(1)=holdMax
          previous(2)=afterSell
    }

    //println("Max value buy, cool, sell " + next.mkString(","))

    previous.max
  }


  trait Action {
    override def toString: String = getClass.getSimpleName.stripSuffix("$")
  }

  object Buy extends Action

  object CoolDown extends Action

  object Sell extends Action


  def main(args: Array[String]): Unit = {

    val test0 = maxProfit(Array(2))
    assert(test0==0,test0)


    val test1 = maxProfit(Array(1,2,3,0,2))
    assert(test1==3,test1)


    val test3 = maxProfit(Array(1,4,7,6,8,2,12))
    assert(test3==16,test3)

  }

}
