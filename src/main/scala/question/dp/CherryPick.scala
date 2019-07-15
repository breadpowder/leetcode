package question.dp

/*
741. Cherry Pickup

Share
In a N x N grid representing a field of cherries, each cell is one of three possible integers.



0 means the cell is empty, so you can pass through;
1 means the cell contains a cherry, that you can pick up and pass through;
-1 means the cell contains a thorn that blocks your way.


Your task is to collect maximum number of cherries possible by following the rules below:


Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.



Example 1:

Input: grid =
[[0, 1, -1],
 [1, 0, -1],
 [1, 1,  1]]
Output: 5
Explanation:
The player started at (0, 0) and went down, down, right right to reach (2, 2).
4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
Then, the player went left, up, up, left to return home, picking up one more cherry.
The total number of cherries picked up is 5, and this is the maximum possible.


Note:

grid is an N by N 2D array, with 1 <= N <= 50.
Each grid[i][j] is an integer in the set {-1, 0, 1}.
It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.


 */

object CherryPickup {


  //First try, time limit reached since it is an expoential algorithm to explore all path between A - > B

/*  def cherryPickup(grid: Array[Array[Int]]): Int = {

    //DFS, first reach (N-1,N-1) and come back

    var total = 0
    val size = grid.length

    var count = 0
    def DFS(i: Int, j: Int, isFoward: Boolean, path_total: Int): Unit = {


      val value = grid(i)(j)
      val current_total = path_total + value
      count +=  1
      println(i + ", " + j + " " + isFoward + " " + current_total + " "  + count )

      grid(i)(j) = 0


      var next: List[(Int, Int)] = List.empty[Tuple2[Int, Int]]

      if (isFoward) {

        //TODO check why  if (i == j == size - 1) this does work
        if (i == size - 1 && j==size-1) {

          DFS(size-1, size-1, false, current_total)
        }

        else {
          val next_candidate = {

            if (i == size - 1) List((i, j + 1))

            else if (j == size - 1) List((i + 1, j))

            else List((i + 1, j), (j + 1, i))
          }

          next = next_candidate.filter(tuple => grid(tuple._1)(tuple._2) != -1)

        }
      }

      else {

        if (i == 0 && j == 0) {
          total = if (current_total > total) current_total else total
        }

        else {
          val next_candidate = {
            if (i == 0) List((i, j - 1))

            else if (j == 0) List((i - 1, j))

            else List((i - 1, j), (i, j-1))
          }

          next = next_candidate.filter(tuple => grid(tuple._1)(tuple._2) != -1)

        }
      }

        next.foreach { next =>
          DFS(next._1, next._2, isFoward,current_total)
        }

      grid(i)(j) = value


    }
    DFS(0, 0, true, total)

    total

  }*/

  def cherryPickup(grid: Array[Array[Int]]): Int = {

     //DP: two paths starting from (0,0),  DP(x,y,p),  represents the maximum cherries can be picked at (x,y). (p,x+y-p)

     // Orignial problem DP(N-1,N-1, N-1), starting from DP(0,0,0)

     // DAG: DP(x, y, p) = grid(x, y) + grid(p,q） + max{DP(x-1,y,p-1),DP(x-1,y,p), DP(x, y-1, p-1), DP(x, y-1,p)}
      val len = grid.length

      val dp = Array.fill[Int](len, len, len)(-1)

      dp(0)(0)(0) = grid(0)(0)

      for{
        i <-  0 to len -1
        j <- 0 to len -1
        p <- 0 to len-1
        val q = i + j - p if (q >=0 && q< len & i + j >0)
      }{

        val max = {
          val tuple = List((i - 1,j,p), (i,j-1,p), (i-1, j, p-1), (i,j-1, p-1))
          var m = -1
          tuple.filter(a => a._1 >= 0 && a._2>=0 && a._3>=0).foreach { tuple =>
            m = Math.max(dp(tuple._1)(tuple._2)(tuple._3),m)
          }
          m
        }

        val add = {
          if(i == p) grid(i)(j)
          else grid(i)(j) + grid(p)(q)
        }

        dp(i)(j)(p) = {
          if (max != -1 && grid(i)(j) != -1 && grid(p)(q) != -1)  max + add
          else -1
        }

     // println("i,j,p:" +  i + "," + j + "," + p + "," + "max:" +  max +  ",add:" + add + "," +  dp(i)(j)(p) )

      }


     val result = dp(len-1)(len-1)(len-1)

      Math.max(result, 0)

  }

  def main(args: Array[String]): Unit = {

   val test1 = Array(Array(0,1,-1), Array(1,0,-1), Array(1,1,1))

    val test1_result =cherryPickup(test1)

    assert(test1_result==5, test1_result)
    

    val test2 = Array(Array(1,1,1,1,0,0,0),Array(0,0,0,1,0,0,0),Array(0,0,0,1,0,0,1),Array(1,0,0,1,0,0,0),Array(0,0,0,1,0,0,0),Array(0,0,0,1,0,0,0),Array(0,0,0,1,1,1,1))
    assert(cherryPickup(test2)==15)


    val test3 = Array(Array(0,0),Array(0,0))
    assert(cherryPickup(test3)==0)

  }

}
