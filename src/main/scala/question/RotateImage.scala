package question


object RotateImage {


  def rotate(matrix: Array[Array[Int]]): Unit = {

    //given (i,j) becomes (j,n-i)
    // from outer cycle to inner 0 outer with = 0,1 to n/2, inner from 0 to n-1

    if (matrix.isEmpty) return matrix


    val l = matrix.length
    for{
      i <- 0 to l/2
      j <- i to l - 2 - i
    }{

      var nextRow = j
      var nextCol = l-1-i
      var next = matrix(i)(j)

      //Condition should be OR not END
      while(nextRow!=i|| nextCol!=j) {

          var tmp = matrix(nextRow)(nextCol)
          matrix(nextRow)(nextCol) = next
          next = tmp

          tmp = nextCol
          nextCol = l-1 - nextRow
          nextRow = tmp
          //next = matrix(nextRow)(nextCol)

      }

      matrix(i)(j) = next


    }






  }

  def main(args: Array[String]) ={

    var arr = Array(
      Array(1,2,3),
      Array(4,5,6),
      Array(7,8,9)
    )

    rotate(arr)

    arr.foreach(a => println(a.mkString(",")))


    arr =Array(
      Array(1,2,3,4),
      Array(5,6,7,8),
      Array(9,10,11,12),
      Array(13,14,15,16)
    )

      rotate(arr)

    arr.foreach(a => println(a.mkString(",")))

  }
}