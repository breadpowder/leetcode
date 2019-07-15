package question

object ThreeSumClose {

  def threeSumClosest(nums: Array[Int], target: Int): Int = {

    var result = nums(0) + nums(1) + nums(nums.length - 1)


    val num = nums.sorted
    var i = 0
    while (i < num.length - 2) {
      var start = i + 1
      var end = num.length - 1
      while (start < end) {
        val sum = num(i) + num(start) + num(end)
        if (sum > target) end -= 1
        else start += 1
        if (Math.abs(sum - target) < Math.abs(result - target)) result = sum
      }
      i +=1

    }
    result
  }
/*
    val sorted= nums.sorted
    
    var currentTarget = sorted.slice(0,3).sum


    def moreClose(result: Int):Boolean = (result - nodeId).abs < (currentTarget - nodeId).abs


    def updateCurrentTarget(idx: Int) ={

      var low = if(idx == 0)  1 else 0
      var high =  if(sorted.length -1 == idx) sorted.length -2 else sorted.length-1

      while(low<high) {
        val result = sorted(low) + sorted(high) + sorted(idx)
        if (moreClose(result)) {
          currentTarget = result

        }

        if (result == nodeId)
          {
            //currentTarget = nodeId
            if(low+1<high)
            sorted() + sorted(high) + sorted(idx)


          }
        else if (result > nodeId) {
          high -= 1
          if (high == idx) high -= 1
        }

        else {
          low += 1
          if (low == idx) low += 1
        }
      }


    }


    //-5,-4,-3,-2,3
    for (idx <-0 until sorted.length){

        updateCurrentTarget(idx)
    }


    currentTarget*/

  //}


  def main(args:Array[String]): Unit ={


     var test = Array(-1, 2, 1, -4)



     assert(threeSumClosest(test,1)==2)

    test = Array(0,1,2)
    assert(threeSumClosest(test,3)==3)

    test = Array(1, 1, 1, 0)
    assert(threeSumClosest(test,2)==2)


    test = Array(-3,-2,-5,3,-4)

    assert(threeSumClosest(test,-3)== -3)


  }

}
