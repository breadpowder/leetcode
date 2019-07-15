/*
417. Pacific Atlantic Water Flow
Medium

646

97

Favorite

Share
Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean"
touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */

object PacificAtlanticOcean {


  def pacificAtlanticNaive(matrix: Array[Array[Int]]): List[Array[Int]] = {
    var answer = List.empty[Array[Int]]

    if (matrix.isEmpty) return answer


    val rowNum = matrix.length
    val colNum = matrix(0).length

    val next = Array(1, 0, -1, 0, 1)

    //TODO define a two-dimension array, Integer.MIN_VALUE
    val result  = Array.fill(rowNum, colNum)(false)



    def dfsWithBackTracking(m: Int, n: Int, h: Int): Int = {

   /*   if((m==0 && n==colNum - 1) || (m == rowNum-1 && n== 0))  {
        if(matrix(m)(n)<=h) {
          return 3
        }

        return 0
      }*/

      //TODO MUST SMALLER THAN, OTHERWISE THE EDGE WILL BE NOT BE CONSIERED FOR BOTH SIDES
     if(m<0 || n <0) {
        return 1
      }


      else if(m>rowNum-1 || n >colNum - 1) {
        return 2
      }

      else if(matrix(m)(n)>h) {
        return 0
      }

      else if(result(m)(n)) {
        return 3
      }


          val orig = matrix(m)(n)
          matrix(m)(n) = Integer.MAX_VALUE


         val visited =  (dfsWithBackTracking(m + 1, n, orig) |
           dfsWithBackTracking(m - 1, n, orig) |

           dfsWithBackTracking(m , n-1, orig) |

           dfsWithBackTracking(m , n + 1, orig))

          matrix(m)(n) = orig
          return  visited

     }


    for {i <- 0 to rowNum-1
    j <- 0 to colNum-1
    }{

        val canVisit = (dfsWithBackTracking(i, j, matrix(i)(j)) ==3)

        if(canVisit) result(i)(j) = true

    }


    for{
      i <- 0 until rowNum
      j <- 0 until colNum
    }{

      if(result(i)(j)){

        answer = Array(i,j):: answer
      }
    }

    answer
  }




    def pacificAtlantic(matrix: Array[Array[Int]]): List[Array[Int]] = {

    var result = List.empty[Array[Int]]

    if(matrix.isEmpty) return result

    val rowNum = matrix.length
    val colNum = matrix(0).length

    val next = Array(1, 0, -1, 0, 1)


    //TODO define a two-dimension array, Integer.MIN_VALUE
    val resultP =  Array.fill[Boolean](rowNum, colNum)(false)
    val resultA =  Array.fill[Boolean](rowNum, colNum)(false)

    //do dfs from both pacific and atlantic
    for(idx<- 0 to colNum -1){

      dfs(0, idx, Integer.MIN_VALUE, resultP)
      dfs(rowNum-1, idx, Integer.MIN_VALUE, resultA)
    }


    for(idx<- 0 to rowNum-1){
      dfs(idx, 0, Integer.MIN_VALUE, resultP)
      dfs(idx, colNum-1, Integer.MIN_VALUE, resultA)
    }


    def dfs(m: Int, n: Int, h: Int, resultSet: Array[Array[Boolean]]): Unit = {

      if(m<0 || m >= rowNum || n<0 || n>= colNum || matrix(m)(n)<h) return

      if(resultSet(m)(n)) return

      resultSet(m)(n) = true

      for( idx <-  0 to 3){

        val nextX=  m + next(idx)

        val nextY = n + next(idx+1)

        dfs(nextX, nextY, matrix(m)(n), resultSet)

      }
    }



    for{
      row <- 0 until rowNum
      col <- 0 until colNum
      if(resultP(row)(col) && resultA(row)(col))
    }{

      result = Array(row,col) :: result
    }
    result

  }

  def main(args: Array[String]): Unit = {


    val matrix = Array(
      Array(1,2,2,3,5),
      Array(3,2,3,4,4),
      Array(2,4,5,3,1),
        Array(6,7,1,4,5),
      Array(5,1,1,2,4)
    )


    pacificAtlanticNaive(matrix).foreach(arr => println(arr.mkString(",")))
  }
}
