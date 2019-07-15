package question.divideAndConquer.binarySearch.rangeSearch

//TODO please read this section for a O(n) solution
// http://keithschwarz.com/interesting/code/?dir=find-duplicate

/*

287
Find the Duplicate Number


Favorite

Share
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2
Example 2:

Input: [3,1,3,4,2]
Output: 3
*/

object FindTheDuplicateNumber {



  def findDuplicate(nums: Array[Int]): Int = {

  val length = nums.length

  //TODO search space is from 1 to length -1
  //TODO count(nums in ( 1 to mid) > mid)
  //TODO stop condition is when low = high

   def binarySearchRange(low: Int=1, high: Int=length-1): Int ={

     if(low==high) return low

     val mid = low +  (high - low)/2

     var count = 0
     nums.foreach{ i =>
       if(i <= mid) count +=1
     }

     if(count>mid) binarySearchRange(low, mid)

     else binarySearchRange(mid+1, high)

   }


  binarySearchRange()

}


def main(args: Array[String]): Unit = {

  assert(findDuplicate(Array(1,3,4,2,2))==2)
  assert(findDuplicate(Array(3,1,3,4,2))==3)
}
}
