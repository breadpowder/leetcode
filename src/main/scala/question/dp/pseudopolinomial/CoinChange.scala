package question.dp.pseudopolinomial

//TODO

//see search and greedy algorithm https://www.youtube.com/watch?v=uUETHdijzkA

object CoinChange {

  def coinChange(coins: Array[Int], amount: Int): Int = {


    if(amount<0) return  -1

    if(amount==0) return  0

   if(coins.length==1) if(amount % coins.head ==0) return amount/coins.head else return -1

   val coinSorted = coins.filter(_<=amount).sorted

    /*
        //TODO initiate orignial problem 0
        val queues = new collection.mutable.LinkedHashSet[Int]()
         queues.add(0)

        var step= 0

        var dp = queues.toArray
        while(dp.nonEmpty && dp.head < amount) {
          step += 1
          for {
            key <- dp
          } {
            for (coin <- coinSorted) {
              if (key + coin < amount && key + coin >= dp.last - coinSorted.last) {
                queues.add(key + coin)
              }
              else if(key + coin == amount)
                {
                  return step
                }
              queues.remove(key)
          }
            dp = queues.toArray
        }


        }
        -1*/

      val dp = Array.fill[Int](amount)(-1)
      for(coin <- coinSorted){
      dp(coin-1) = 1
   }

   for{
     total <-  0 until amount
     coin <- coinSorted
   }{

     if(dp(total) != -1 && total+ coin<amount ){
      if( dp(total+coin)== -1 || dp(total)+1<dp(total+coin)) dp(total+coin) = dp(total) + 1
     }

   }

    return dp(amount-1)

  }

  def main(args: Array[String]): Unit = {

    println(coinChange(Array(1,2,5),11))

    println(coinChange(Array(1,2147483647),2))
    println(coinChange(Array(1),2147483647))

    println(coinChange(Array(2),3))

    println(coinChange(Array(1),1))

    println(coinChange(Array(281,20,251,251),7323))


    println(coinChange(Array(5,43),200))

    println(coinChange(Array(1,2),2))

    println(coinChange(Array(186,419,83,408),
    6249))
  }
}
