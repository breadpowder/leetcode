package question



/*
279
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.


//
 */

//Use BFS skip the queue, quick fail and visit once do helps,
object PerfectSquare {

  def numSquares(n: Int): Int = {

    if(n<=0) return 0
    //construct array that with possible array find j smaller than sqrt(n)

    val candidates = (1 to Math.sqrt(n.toDouble).toInt).toList.map(a=>a*a)

    // do BFS in this array if the sum is equals stop
    var steps = 0
    var visited = scala.collection.immutable.HashSet[Int](0)

    val visitedMap = new scala.collection.mutable.HashMap[Int, Int]()


    while(steps<=n && !visitedMap.contains(n)) {

      //v<-visited
      steps = steps+1

      import scala.util.control.Breaks._

      breakable{

      visited =  for {
        v <- visited
        c <- candidates
        val tmp= v+c
        if (tmp <=n) && (!visitedMap.contains(tmp))
      } yield {

          if(tmp==n) {
            visitedMap.put(tmp, steps)
            break
          }

          else
            visitedMap.put(tmp, steps)
           tmp
      }
      }

    }

    steps


  }

  def main(args: Array[String]): Unit = {

    val test1 = numSquares(12)

    assert(test1==3, test1)

    val test2 = numSquares(13)

    assert(test2==2, test2)

    val test3 = numSquares(26)
    assert(test3==2, test3)
  }

}
