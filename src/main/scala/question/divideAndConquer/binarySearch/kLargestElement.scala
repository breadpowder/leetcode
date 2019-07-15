package question.divideAndConquer.binarySearch

object kLargestElement {



  val rand = new java.util.Random(System.currentTimeMillis())
  def findKthLargest(nums: Array[Int], k: Int): Int = {



    //  find a pivot split data to high, pivot, low
    // if k < pivot idx+1  ,
     // if k> pivot idx + 1 , nums(pivot,) k = k-pivot idx -1

    def switch(i:Int, j:Int) = {

      val tmp= nums(i)
      nums(i) = nums(j)
      nums(j) = tmp
    }

    def findKthRecursive(low: Int, high:Int, k:Int):Int ={

      var idx = low + rand.nextInt(high-low+1)

      val pivot = nums(idx)
      switch(idx, high)

      var nextToSwitch = low

      //switch to high - 1
      for {
        start <- low to high -1
        if (nums(start)>=pivot)
      }{
        switch(nextToSwitch, start)
        nextToSwitch +=1
      }

      switch(high, nextToSwitch)
      //TODO ATTENTION k now is updated, k should be equals to NEW UPDATED INDEX
      if(k==nextToSwitch - low +1)
            return nums(nextToSwitch)
      else if  (k<nextToSwitch - low+ 1)
            return findKthRecursive(low,nextToSwitch-1,k)
      else
            return findKthRecursive(nextToSwitch+1,high,k-(nextToSwitch-low+1))
    }


    findKthRecursive(0, nums.length-1,k)

  }

  def main(args: Array[String]) : Unit ={

    var arr = Array(3,2,1,5,6,4)

    assert(findKthLargest(arr,2) == 5)

     arr = Array(3,2,3,1,2,4,5,5,6)

   assert(findKthLargest(arr,4) == 4)

  }
}
