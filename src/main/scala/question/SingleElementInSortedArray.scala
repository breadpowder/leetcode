package question


/*
540. Single Element in a Sorted Array
Medium

670

57

Favorite

Share
Given a sorted array consisting of only integers where every element appears twice except for one element which appears once. Find this single element that appears only once.

Example 1:
Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:
Input: [3,3,7,7,10,11,11]
Output: 10
Note: Your solution should run in O(log n) time and O(1) space.

 */
object SingleElementInSortedArray {

   //TODO number should be 2k+1, k = (len -1)/2
   // TODO look at kth pair,
  // if there is one record, find , then if left and the right is the same stay in this half, otherwise look at right half


  def singleNonDuplicate(nums: Array[Int]): Int = {


      def nonDuplicate(start: Int=0, end: Int=nums.length-1): Int = {

        println(start + "," + end)
        if(start - end ==0 ) return nums(start)

        if(end - start< 4)  {
          if (nums(start) != nums(start+1)) return nums(start)
          else  return nums(end)
        }

        //TODO always remeber middleIdx
        val middleIdx = start + (end - start)/4*2

        val middle = nums(middleIdx)

        if(middle != nums(middleIdx-1) && middle!= nums(middleIdx+1)) return middle

        else if(middle != nums(middleIdx+1)) nonDuplicate(start, middleIdx-2)

        else nonDuplicate(middleIdx + 2, end)

      }

    nonDuplicate()

  }


  def main(args: Array[String]): Unit = {

    val inputs = TestUtility.readFile("540Input.txt")
    val arr = inputs(0).split(",").map(_.toInt)

   /* println(singleNonDuplicate(Array(1,1,2,3,3,4,4,8,8)))

    println(singleNonDuplicate(Array(3,3,7,7,10,11,11)))*/

    TestUtility.time{

      println(singleNonDuplicate(arr))
    }
  }

}
