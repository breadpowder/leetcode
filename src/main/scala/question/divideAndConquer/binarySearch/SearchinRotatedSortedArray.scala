package question.divideAndConquer.binarySearch

/*
33.
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a nodeId value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], nodeId = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], nodeId = 3
Output: -1

//TODO notice the condition is based on order kept, -> if it is in the two increasing section or not

 */
object SearchinRotatedSortedArray {

  def search(nums: Array[Int], target: Int): Int = {


    if(nums.isEmpty) return -1

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


}
