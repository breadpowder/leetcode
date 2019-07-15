package question

import scala.collection.mutable.ArrayBuffer

/*

300
Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
 */

/*
really shit code of version one

do version two
 */
object LongestIncreasingSubsequence {

  def lengthOfLIS(nums: Array[Int]): Int = {

    if (nums.isEmpty) return 0

    val dpArr = Array.fill(nums.length, nums.length)(1)

    val dpMinMax = Array.ofDim[Tuple2[Int, Int]](nums.length, nums.length)

    var (max, min) = (nums(0), nums(0))

    for {
      i <- 0 until nums.length
    } {
      dpMinMax(i)(i) = (nums(i), nums(i))
    }

    for {
      len <- 1 until nums.length
      start <- 0 until nums.length
      if (start + len) < nums.length
    } {

      dpArr(start)(start + len) = {

        var prefixAdd: Boolean = false
        var suffixAdd: Boolean = false


        val prefix = {
          val add = if (nums(start) < dpMinMax(start + 1)(start + len)._1) {
            //dpMinMax(start)(start + len) = (nums(start), dpMinMax(start + 1)(start + len)._2)
            prefixAdd = true

            1
          } else {
            //dpMinMax(start)(start + len) = dpMinMax(start + 1)(start + len)

            0
          }

          add + dpArr(start + 1)(start + len)

        }
        val suffix = {
          val add = if (nums(start + len) > dpMinMax(start)(start + len - 1)._2) {
            //dpMinMax(start)(start + len) = (dpMinMax(start)(start + len-1)._1, nums(start + len))
            suffixAdd = true
            1
          } else {
            //dpMinMax(start)(start + len) = dpMinMax(start)(start + len-1)
            0
          }

          add + dpArr(start)(start + len - 1)
        }


        val v = if (prefix >= suffix) {
          if (prefixAdd) {
            dpMinMax(start)(start + len) = (nums(start), dpMinMax(start + 1)(start + len)._2)

          }
          else {
            dpMinMax(start)(start + len) = dpMinMax(start + 1)(start + len)
          }
          prefix
        }

        else {
          if (suffixAdd) {
            dpMinMax(start)(start + len) = (dpMinMax(start)(start + len - 1)._1, nums(start + len))
          }
          else {
            dpMinMax(start)(start + len) = dpMinMax(start)(start + len - 1)
          }
          suffix
        }

        // println(s"dpArr[$start][${start + len}]=$v, ${dpMinMax(start)(start + len)}" )
        v
      }

    }


    dpArr(0)(nums.length - 1)

  }

  def lengthOfLISV2(nums: Array[Int]): Int = {

    if (nums.isEmpty) return 0

    val arr = Array.fill(nums.length)(0)

    for (i <- nums.length - 1 to 0 by -1) {
      var maxIdx = i
      for (j <- i + 1 to nums.length - 1) {
        if (nums(j) > nums(i) && arr(j) > arr(maxIdx)) maxIdx = j
      }

      arr(i) = if (maxIdx != i) {
        arr(maxIdx) + 1
      } else 1
    }

    //println(arr.mkString(","))

    arr.max
  }


  //Here is an algorithm also https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
  def lenghtOfLIVPatienceSort(nums: Array[Int]): Int = {

    //ALso Algorithm keep N piles, N is the longest subsequence

    // TODO step1 search the first pile idx that arrayBuffer(idx) >= K
    //TODO step2 create a new pile or updated the searched pipe
    val arrayBuffer: ArrayBuffer[Int] = new ArrayBuffer[Int]()

    def size = arrayBuffer.size


    def binarySearchAndUpdate(i: Int) = {


      if(arrayBuffer.size==0) {

        arrayBuffer.append(i)
      }

      else {

        binarySearch(0, arrayBuffer.size-1)
      }



      def binarySearch(start: Int, end: Int): Unit = {

        //TODO larger than or no less than (>=)  (3,10) insert 8
        if (start > end) {

          if (start == size) {
            arrayBuffer.append(i)
          }

          else {
            arrayBuffer.update(start, i)
          }
        }

        else {

          val mid = (start + end) / 2

          if (arrayBuffer(mid) < i) binarySearch(mid + 1, end)

          else {
            binarySearch(start, mid - 1)
          }
        }

      }


    }



    for {
      num <- nums
    } {

      binarySearchAndUpdate(num)

    }

    arrayBuffer.size


  }






    def main(args: Array[String]): Unit = {

 /*  val test0 = Array(1)
    assert(lengthOfLISV2(test0)==1, lengthOfLISV2(test0))


    val test1 = Array(5,6,3)
    assert(lengthOfLISV2(test1)==2, lengthOfLISV2(test1))


    val test2 = Array(10,9,2,5,3,7,101,18)
    assert(lengthOfLISV2(test2)==4, lengthOfLISV2(test2))


    val test3 = Array(4,10,4,3,8,9)
    assert(lengthOfLISV2(test3)==3, lengthOfLISV2(test3))*/


      val test1 = Array(5,6,3)
      assert(lenghtOfLIVPatienceSort(test1)==2, lengthOfLISV2(test1))


      val test2 = Array(10,9,2,5,3,7,101,18)
      assert(lenghtOfLIVPatienceSort(test2)==4, lengthOfLISV2(test2))

      val test3 = Array(4,10,4,3,8,9)
      assert(lenghtOfLIVPatienceSort(test3)==3, lenghtOfLIVPatienceSort(test3))
    }


}
