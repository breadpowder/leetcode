package question

object SpiralOrder {


  def spiralOrder(matrix: Array[Array[Int]]): List[Int] = {


    if(matrix.isEmpty) return List.empty

    val height = matrix.size
    val width = matrix(0).length
    val result = Array.ofDim[Int](height*width)

    var idx=0


    for {
      row <- 0 to height
      if (row < (Math.min(height,width)+1)/2)
    } {

      val maxCol = width-1 - row
      val maxRow = height-1 - row


      for (upper <- row to maxCol)
      //a cycle
      {
        result(idx) = matrix(row)(upper)
        idx +=1
      }

      for {
        right <- row+1 to maxRow
      } {
        result(idx) = matrix(right)(maxCol)
        idx +=1

      }

      //TODO guard

      for {
        down <- maxCol-1 to row+1 by -1
        if maxRow > row
      } {
        result(idx) = matrix(maxRow)(down)
        idx +=1

      }


      for
      {left <- maxRow to row+1 by -1
        if maxCol>row
      } {
        result(idx) = matrix(left)(row)
        idx +=1

      }
    }

    result.toList
  }


  def main(args: Array[String]): Unit = {

    spiralOrder(Array(Array(1,2,3,4),Array(5,6,7,8),Array(9,10,11,12))).foreach(println)

   assert(spiralOrder(Array(Array(1,2,3,4),Array(5,6,7,8),Array(9,10,11,12))).sameElements(Array(1,2,3,4,8,12,11,10,9,5,6,7)))

   assert(spiralOrder(Array(Array(1,2,3,4))).sameElements(Array(1,2,3,4)))

    assert(spiralOrder(Array(Array(1),Array(2))).sameElements(Array(1,2)))
  }

}
