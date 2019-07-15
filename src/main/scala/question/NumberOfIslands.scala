package question

/*


Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
 */

object NumberOfIslands {

  def numIslands(grid: Array[Array[Char]]): Int = {

    if(grid.isEmpty || grid(0).isEmpty) return 0

    val height = grid.length

    val width = grid(0).length

    val gridSearchIdx = Array.ofDim[Boolean](height,width)

    var count =0

    def search(h: Int, w: Int) ={

      val queue = new collection.mutable.Queue[Tuple2[Int,Int]]()

      queue.enqueue((h,w))
      gridSearchIdx(h)(w) = true
      while(queue.nonEmpty){

        val head = queue.dequeue

        val ( r:Int, c:Int) = (head._1, head._2)

        //TODO searchReferechTable gridSearchIdx needs to mark visited index, not for char==1, the reason is if only marked for those cell with 1,
        // 1,1,1,0
        // 0,0,1,0
        //WRONG!!1 gridSearchIdx(r)(c)= true
        verifyAndEnqueue(r-1,c)
        verifyAndEnqueue(r+1,c)
        verifyAndEnqueue(r,c-1)
        verifyAndEnqueue(r,c+1)

      }


      def verifyAndEnqueue(r: Int, c: Int) ={
        if(r>=0 && r<=height -1  && c>=0 && c<=width-1 && grid(r)(c)=='1' && !gridSearchIdx(r)(c)){
          //println(s"enqueing [$r,$c]")
          queue.enqueue((r,c))
          gridSearchIdx(r)(c) =true
        }


      }

    }




    for{
        h <- 0  to height-1
        w <- 0 to width-1
        if grid(h)(w) =='1' && gridSearchIdx(h)(w)==false
    }{

        count += 1
       // println(s"searching $h,$w")
        search(h,w)
    }

    count
  }

  def main(args: Array[String]): Unit = {

/*   var grid = Array("11000","11000","00100","00011")

    TestUtility.time{
      println(numIslands(TestUtility.convertToArrayChar(grid)))
    }*/


    val test = Array(Array[Char]('1','1','1','1','1','0','1','1','1','1','1','1','1','1','1','0','1','0','1','1')
     ,Array('0','1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','1','0'),
      Array('1','0','1','1','1','0','0','1','1','0','1','1','1','1','1','1','1','1','1','1'),Array('1','1','1','1','0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'),
      Array('1','0','0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'),Array('1','0','1','1','1','1','1','1','0','1','1','1','0','1','1','1','0','1','1','1'),    Array('0','1','1','1','1','1','1','1','1','1','1','1','0','1','1','0','1','1','1','1'),Array('1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','0','1','1'),
      Array('1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','1','1','1','1','1'),Array('1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'),
      Array('0','1','1','1','1','1','1','1','0','1','1','1','1','1','1','1','1','1','1','1'),Array('1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'),
      Array('1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'),Array('1','1','1','1','1','0','1','1','1','1','1','1','1','0','1','1','1','1','1','1'), Array('1','0','1','1','1','1','1','0','1','1','1','0','1','1','1','1','0','1','1','1'),Array('1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','1','1','0'), Array('1','1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','0','0'),Array('1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'), Array('1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'),Array('1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1')
    )


    TestUtility.time{
      println(numIslands(test))
  }
  }

}
