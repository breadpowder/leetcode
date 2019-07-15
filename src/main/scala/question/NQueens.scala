package question

/*51. N-Queens
Hard

840

40

Favorite

Share
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
[".Q..",  // Solution 1
"...Q",
"Q...",
"..Q."],

["..Q.",  // Solution 2
"Q...",
"...Q",
".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
Review: algorithm

https://leetcode.com/problems/n-queens-ii/discuss/20048/Easiest-Java-Solution-(1ms-98.22)
*/
object NQueens {


  def solveNQueens(n: Int): List[List[String]] = {


    //BACKTRACKING each position check if available, recursive slove the rest
    //TODO use array[Array[Char] for List[String] for fast

    var result = List.empty[List[String]]

    var arr : Array[Array[Char]] = null

    var qState: List[(Int, Int)] = null

     def solveNQueensWithIdx(N: Int, r: Int, c: Int):Unit = {

            if(N==0) {
              // right associactive ::= ?
              //result = current :: result
              result = arr.map(a => a.mkString("")).toList :: result
              return
            }

            if(r > qState.size) return

            var current = (r,c)


       //TODO  backtracking all solutions need to enuermating all availablities not the first one

        //TODO state tracking is global varible and defined outside
       // TODO use array[array[char]] for mutable state

            while (current._1 < n && current._2 < n) {

              //get next candidate (i,j)
              while ((current._1 < n && current._2 < n) && !isValid(current._1, current._2)) {
                arr(current._1)(current._2) = '.'
                current = getNext(current._1, current._2)
              }


              if (current._1 >= n || current._2 >= n) return


              arr(current._1)(current._2) = 'Q'
              qState = (current._1, current._2) :: qState
              val next = getNext(current._1, current._2)
              solveNQueensWithIdx(N - 1, next._1, next._2)
              arr(current._1)(current._2) = '.'
              //TODO tail complexity qState.tail
              qState = qState.tail
              current = getNext(current._1, current._2)
            }

     }

    def getNext(r: Int, c: Int): (Int, Int) = {

      if(c<n-1) (r,c+1)
      else (r+1,0)

    }



    def isValid(r: Int, c:Int) : Boolean ={

      qState.foreach {

        state => {
          val row = state._1
          val col = state._2

          if(r==row || c == col || (r-row).abs == (c-col).abs){

            return  false
          }

        }
      }

      true
    }


    /*    for {
          i <- 0 to n - 1
          j <- 0 to n - 1
        }{*/
      arr = Array.fill(n,n)('.')
      qState = List.empty[(Int,Int)]
      solveNQueensWithIdx(n, 0, 0)
    //}
    result

  }


  def main(args: Array[String]): Unit = {

      printResult(1)

      printResult(2)

      printResult(3)


      printResult(4)

      printResult(5)




  }

    def printResult(n: Int): Unit ={

      println(s"Result [$n]")

     val result = solveNQueens(n)

      result.foreach{
        s => println("[")
          s.foreach(println)
          println("]")
      }

    }


}
