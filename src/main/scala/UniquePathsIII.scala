
/*
980. Unique Paths III
Hard

Share
On a 2-dimensional grid, there are 4 types of squares:

1 represents the starting square.  There is exactly one starting square.
2 represents the ending square.  There is exactly one ending square.
0 represents empty squares we can walk over.
-1 represents obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.



Example 1:

Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
Explanation: We have the following two paths:
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
Example 2:

Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
Output: 4
Explanation: We have the following four paths:
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
*/

//Take aways:

//1. Condtions missing: if reach end before hit non-blocking sqaures, disabllowed

// 2. the end of remaining should be -1 not 0 given the first call also minus -1

//3. == not =
object UniquePathsIII {

  def uniquePathsIII(grid: Array[Array[Int]]): Int = {

    if(grid.isEmpty) return 0

    val rows =  grid.length
    val cols = grid(0).length
    val flags = Array.ofDim[Boolean](rows, cols)

    var remainingSquares = grid.map(_.count(_==0)).sum

    def findIdxByValue(v: Int): (Int, Int) = {
      for {
        (arr,x) <- grid.iterator.zipWithIndex
        (a, y) <- arr.iterator.zipWithIndex
        if grid(x)(y) == v
      } yield(x, y)}.toList.head


    val (end_x, end_y) : (Int, Int) = findIdxByValue(2)

    val (start_x, start_y): (Int, Int) = findIdxByValue(1)

    var answer:Int = 0

    val next = Array(0, 1, 0, -1, 0)

    def walkRecursive(i: Int, j:Int, depth: Int =0): Unit ={

      if(remainingSquares!= -1 && i ==end_x && j == end_y){
        return
      }

      if(remainingSquares== -1 && i ==end_x && j == end_y) {
        answer +=1
        return
      }


      if(i<0 || i==rows || j<0 || j==cols || grid(i)(j) == -1 || flags(i)(j)){
        return
      }

      if(grid(i)(j)==0)
      remainingSquares -=1
      //println("(x,y):" + (i,j))

      flags(i)(j) = true
      for(idx <- 0 to 3){
        walkRecursive(i + next(idx) , j+ next(idx+1), depth+1)
      }

      flags(i)(j) = false

      remainingSquares +=1


    }

    walkRecursive(start_x, start_y)

    answer

  }

  def main(args: Array[String]): Unit = {

    var test = Array(Array(1,0,0,0),
      Array(0,0,0,0),
      Array(0,0,2,-1))

    println(uniquePathsIII(test))

  }


}
