package question


//TODO condition if guard is difficult to figure out
//SOdifficult to sort our gurad condition
object SortColors {

  def sortColors(nums: Array[Int]): Unit = {


    def switch(i:Int, j:Int) = {
        val tmp  = nums(i)
        nums(i) = nums(j)
        nums(j) = tmp
    }


    nums match {

      case Array() =>
      case Array(_) =>
      case _ => {


        var left = 0

        var right = nums.length - 1
        var idx=0

      for{
        idx <-  0 to nums.length - 1
        if idx<=right
      }
      {
         while(nums(idx)!=1  && idx<=right && left<=idx) {
            if(nums(idx) ==0) {
              switch(idx, left)
              left+=1
            }
            if(nums(idx)== 2){
              switch(idx,right)
              right -=1
            }
          }
        }
      }
    }
  }


  def main(args: Array[String]):Unit ={


      var arr = Array(2,0,1,2,0,1)


      sortColors(arr)
    arr.sameElements(Array(0,0,1,1,2,2))


    arr = Array(0,2,1,2,0,1,1,0,2,1)
        sortColors(arr)

        arr.sameElements(Array(0,0,0,1,1,1,1,2,2,2))




    arr = Array(2,0,2,1,1,0)
    sortColors(arr)
    arr.sameElements(Array(0,0,1,1,2,2))

    arr = Array(0,0,0,0)
    sortColors(arr)
    arr.sameElements(Array(0,0,0,0))

  }
}
