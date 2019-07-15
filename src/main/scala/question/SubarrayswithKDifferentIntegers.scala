package question

/*

https://leetcode.com/problems/subarrays-with-k-different-integers/

992. Subarrays with K Different Integers

Share
Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.

(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

Return the number of good subarrays of A.



Example 1:

Input: A = [1,2,1,2,3], K = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
Example 2:

Input: A = [1,2,1,3,4], K = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].


Note:

1 <= A.length <= 20000
1 <= A[i] <= A.length
1 <= K <= A.length

 */


//TODO pls use sliding window for K exactly
object SubarrayswithKDifferentIntegers {


  //Following are from https://www.youtube.com/watch?v=FZPtxuxArLU the assumption is different integers not more than K

  def subarraysWithKDistinct(A: Array[Int], K: Int): Int = {

    def lessThanKDistinct(n: Int): Int = {

        val len = A.length

        val count = Array.fill(len+1)(0)

        var k = n
        var i = 0
        var ans =0
        for(j<- 0 to A.length-1){

          //decrease available number k when encountering new alpha
              if(count(A(j))==0){
                k -= 1
              }

          count(A(j)) += 1


          //TODO k<0 and while condition counts from(i,j)
          while(k < 0){
              count(A(i)) -=1
              if(count(A(i)) ==0){
                k +=1
              }
            i+=1
          }

          ans += j - i + 1
        }

      ans
    }


  //  println(lessThanKDistinct(K))
//    println(lessThanKDistinct(K-1))

    lessThanKDistinct(K) - lessThanKDistinct(K-1)
  }


  def main(args: Array[String]): Unit = {

    val arr = Array(1,2,1,3,4)

    println(subarraysWithKDistinct(arr,3))

  }

}
