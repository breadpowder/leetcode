package question

object MinPathSum {

  def minPathSum(grid: Array[Array[Int]]): Int = {


    //DP(i,j) = min {
    // DP(i-1,j) if grid(i-1,j) height>i> 0
    // DP(i,j-1) if grid(i, j-1) if width>j>0} + grid(i,j)
    // baseCase(0,0)=0
    // original problem DP(m, n)
    grid.length match{
      case 0 => 0

      case _=> {


        val height = grid.length
        val width = grid(0).length

        val table = Array.ofDim[Int](height, width)


        table(0)(0) = grid(0)(0)
        for(j <- 1 to width -1)
         {
           table(0)(j)= table(0)(j-1) + grid(0)(j)
         }

        for(i<- 1 to height-1)
        {
          table(i)(0) = table(i-1)(0) + grid(i)(0)
        }




        for{ i<- 1 to height-1
            j<- 1 to width -1

        }{

             table(i)(j) = Math.min(table(i-1)(j),table(i)(j-1)) + grid(i)(j)
        }

        table(height-1)(width-1)
      }
    }


  }


  def main(args: Array[String]) ={

      var arr = Array(
        Array(1,3,1),
        Array(1,5,1),
        Array(4,2,1)
      )



     assert(minPathSum(arr)==7)
  }

}
