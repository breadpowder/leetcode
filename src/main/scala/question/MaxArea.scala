package question


object Solution {


  def maxArea(height: Array[Int]) : Int ={

    def area(header:Int, tail:Int) =  Math.min(height(header),height(tail)) * (tail-header)

    //incrrease the aera
    var header = 0
    var tail = height.size-1

    var maxArea = area(header,tail)


    while(header<tail)
    {
      if(height(header)<height(tail)) header = header+1

      else tail = tail-1

      val result = area(header,tail)

      maxArea = {
        if (maxArea < result) result else maxArea
      }

    }

    maxArea

  }


  def main(args: Array[String]) = {

    val result = maxArea(Array(1,8,6,2,5,4,8,3,7))
    assert(result == 49)

  }

}