package question

/*A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Above is a 7 x 3 grid. How many possible unique paths are there?

Note: m and n will be at most 100.

Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
Example 2:

Input: m = 7, n = 3
Output: 28
*/

object UniquePaths {


  def uniquePaths(m: Int, n: Int): Int = {


    val array = Array.ofDim[Int](m, n)

    for(i<- 0 until m){
      array(i)(0) = 1
    }

    for(i<- 0 until n){
      array(0)(i) =1
    }

    for{
      i <- 1 until m
      j <- 1 until n
    }{

      array(i)(j) = array(i)(j-1) + array(i-1)(j)
    }

    return array(m-1)(n-1)

  }

  def main(args: Array[String]): Unit = {

    println(uniquePaths(3,2))
    println(uniquePaths(7,3))
  }

}
