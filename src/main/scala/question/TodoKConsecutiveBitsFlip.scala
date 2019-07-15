package question

/*
995. Minimum Number of K Consecutive Bit Flips
Hard

99

20

Favorite

Share
In an array A containing only 0s and 1s, a K-bit flip consists of choosing a (contiguous) subarray of length K and simultaneously changing every 0 in the subarray to 1, and every 1 in the subarray to 0.

Return the minimum number of K-bit flips required so that there is no 0 in the array.  If it is not possible, return -1.



Example 1:

Input: A = [0,1,0], K = 1
Output: 2
Explanation: Flip A[0], then flip A[2].
Example 2:

Input: A = [1,1,0], K = 2
Output: -1
Explanation: No matter how we flip subarrays of size 2, we can't make the array become [1,1,1].
Example 3:

Input: A = [0,0,0,1,0,1,1,0], K = 3
Output: 3
Explanation:
Flip A[0],A[1],A[2]: A becomes [1,1,1,1,0,1,1,0]
Flip A[4],A[5],A[6]: A becomes [1,1,1,1,1,0,0,0]
Flip A[5],A[6],A[7]: A becomes [1,1,1,1,1,1,1,1]
 */

object TodoKConsecutiveBitsFlip {


  def minKBitFlips(A: Array[Int], K: Int): Int = {
    val flipped = Array.fill(A.size)(0)


    var ans = 0
    var flip = 0
    for{
      i <- 0 to A.size-1
    }{

        if(i>= K) flip ^= flipped(i -K)


     /*     for {
            j <- i to A.size-1
            if A(j) == 0
          } {
            return -1
          }
         }*/

        if((A(i) ^ flip) == 0){

          if(i + K > A.size)  return -1

          flip ^= 1
          ans +=1
          flipped(i) = 1
        }

    }

    ans

  }

  def main(args: Array[String]): Unit = {


     val test3 = Array(0,0,0,1,0,1,1,0)

     println(minKBitFlips(test3,3))
  }


}
