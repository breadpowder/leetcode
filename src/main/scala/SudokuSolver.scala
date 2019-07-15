/*
37 Sodoku solver
A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.


A sudoku puzzle...


 */

/*
Take away:

1. Deep copy multiple dimension array

newArr =arrs.map(arr => arr.clone)

2.  Backtracking
    a. pay attention to all states changes to impact following calls,
        e.g. if(board(i)(j) == '.')
                board(i)(j) = ch
                recursive(board(i+1,(j+1))
                board(i)(j) = '.'

    b. SHOULD USE RETURN ON RESURSION CALLS TO EARLY STOP, rewrite this section

3. multiple dimension array initial
       Array.ofDim[Boolean]()
 */

object SudokuSolver {

    val row =  Array.ofDim[Boolean](9,9)

    val col =  Array.ofDim[Boolean](9,9)

    val grid = Array.ofDim[Boolean](9,9)

 /* val test = {for {
    (arr,x) <- grid.iterator.zipWithIndex
    (a, y) <- arr.iterator.zipWithIndex
    if grid(x)(y) == 2
  } yield(x, y)}.toList.head*/

    def grid_idx(i: Int, j:Int): Int = (i/3) * 3 + j/3

    var isFound = false

  def init(board: Array[Array[Char]]): Unit = {
    for{
      i <- 0 to 8
      j <- 0 to 8
      val idx = board(i)(j) - '1'
      if(board(i)(j)!='.')
    }{

      row(i)(idx) = true
      col(j)(idx) = true
      grid(grid_idx(i,j))(idx) =true
    }
  }

  def getNext(i: Int,j: Int) = if(j==8) (i+1,0) else(i,j+1)

  def solveSudoku(board: Array[Array[Char]]): Unit = {

      val newArr =  board.map(a=> a.clone())


      init(newArr)

      def solveRec(i: Int, j:Int): Unit ={

        if(i==9) {
          isFound = true
          //Note: this is not a deep copy copyToArray
          //newArr.copyToArray(board)

          //deep copy but goes to another vairable
          //board = newArr.map(a => a.clone())

          for {m <- 0 to 8
               n <- 0 to 8
          }{
            board(m)(n) = newArr(m)(n)
          }

          return
        }

        if(newArr(i)(j)=='.'){
          //TODO condition is wrong v=0 while(......){v+=1} because needs to loop outside
          (0 to 8).foreach { v=>
            if (!row(i)(v) && !col(j)(v) && !grid(grid_idx(i, j))(v) && !isFound) {

              row(i)(v) = true
              col(j)(v) = true
              grid(grid_idx(i, j))(v) = true
              newArr(i)(j) = ('1' + v).toChar
              val (next_i, next_j) = getNext(i, j)
              solveRec(next_i, next_j)
              newArr(i)(j) = '.'
              row(i)(v) = false
              col(j)(v) = false
              grid(grid_idx(i, j))(v) = false
            }
          }

        }

        else{

          val (next_i, next_j) = getNext(i,j)
          solveRec(next_i, next_j)
        }

      }

      solveRec(0,0)
    }

    def main(args: Array[String]): Unit = {


      val board: Array[Array[Char]] = Array(
        Array('5', '3', '.', '.', '7', '.', '.', '.', '.'),
        Array('6', '.', '.', '1', '9', '5', '.', '.', '.'),
        Array('.', '9', '8', '.', '.', '.', '.', '6', '.'),
        Array('8', '.', '.', '.', '6', '.', '.', '.', '3'),
        Array('4', '.', '.', '8', '.', '3', '.', '.', '1'),
        Array('7', '.', '.', '.', '2', '.', '.', '.', '6'),
        Array('.', '6', '.', '.', '.', '.', '2', '8', '.'),
        Array('.', '.', '.', '4', '1', '9', '.', '.', '5'),
        Array('.', '.', '.', '.', '8', '.', '.', '7', '9')
      )

      solveSudoku(board)

      board.foreach(arr => println(arr.mkString(",")))
    }
  }
