package question

object RotatedSortArray {

  def search(nums: Array[Int], target: Int): Int = {

    if(nums.isEmpty) -1

    val first = nums(0)


    def binarySearch(l:Int,u:Int):Int= {


      if (l > u) return -1

      val mid = (l + u) / 2
      val cur = nums(mid)
      if (cur == target) return mid

      else {


        if (target >= first && cur >= first || target < first && cur < first) {
          var (low, upper) = if (cur < target) (mid + 1, u) else (l, mid - 1)
          binarySearch(low, upper)

        }

        else {
          var (low, upper) = if (cur < target) (l, mid - 1) else (mid + 1, u)
          binarySearch(low, upper)
        }

      }
    }

    binarySearch(0, nums.length-1)

  }


  def main(args: Array[String]) = {

    var array = Array(4, 5, 6, 7, 0, 1, 2)

    assert(search(array, 5) == 1)

    assert(search(array, 1) == 5)

    assert(search(array, 3) == -1)


    array = Array(1,3)

    assert(search(array,3)==1)

    array = Array(3,1)
    assert(search(array,1)==1)

    array = Array(5,1,3)
    assert(search(array,5)==0)

  }
}