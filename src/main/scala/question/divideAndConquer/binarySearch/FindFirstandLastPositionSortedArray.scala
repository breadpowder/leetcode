package question.divideAndConquer.binarySearch

/*
34. Find First and Last Position of Element in Sorted Array
Medium

Share
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given nodeId value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the nodeId is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], nodeId = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], nodeId = 6
Output: [-1,-1]
 */


//TODO see the code branching at nodeId==value

object FindFirstandLastPositionSortedArray {

  def searchRange(nums: Array[Int], target: Int): Array[Int] = {

    var start, end = -1

    def search(startIdx: Int=0, endIdx: Int=nums.length-1): Unit = {

       if(endIdx<startIdx) return

       val mid = startIdx + (endIdx  - startIdx) /2

       if(nums(mid)==target)
         {
           //val post = if(mid < endIdx)  nums(mid+1) else nums(mid)
           //TODO find first one > nodeId -1 and last one < nodeId + 1 in case class
            //TODO TODOO to prevent （2,2) startIdx = 1, mid = 1, endIdx = 1, we use mid>0 not mid>startIdx
           val pre: Option[Int] = if (mid > 0) Option(nums(mid-1)) else None

           val after: Option[Int] = if(mid < nums.length -1 ) Option(nums(mid + 1)) else None

          pre match {
              case Some(v) if v < target => start = mid
              case None => start = mid
              case _ => search(startIdx, mid-1)
            }

          after match {

             case Some(v) if v > target => end = mid
             case None => end = mid
             case _ => search(mid + 1, endIdx)

           }
         }

           else if(nums(mid)<= target) search(mid+1, endIdx)

           else search(startIdx, mid-1)

    }

    search()

    Array(start, end)


  }

  def main(array: Array[String]): Unit = {


    println(searchRange(Array(2,2), 2).mkString(","))

    println(searchRange(Array(2,2,2), 2).mkString(","))


    println(searchRange(Array(5,7,7,8,8,10),8).mkString(","))


    println(searchRange(Array(5,7,7,8,8,10),6).mkString(","))

    println(searchRange(Array(5,7,7,8,8,10),5).mkString(","))


  }

}
