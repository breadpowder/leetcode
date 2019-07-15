package question.dp

/*
312
Burst Ballons
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons.
 If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i.
 After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:

You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Input: [3,1,5,8]
Output: 167
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167


Below analysis is based on last step ...what's the problem?
dp(i,j) = max(coins(k) + dp(i, k-1) + dp(k+1,j) for i<=k<=j)

init dp(i)(i) = nums(i)

orign dp(0)(n)=?

Analysis. That is reverse thinking. Like I said the coins you get for a balloon does not depend on the balloons already burst. Therefore
instead of divide the problem by the first balloon to burst, we divide the problem by the last balloon to burst.

 */
object BurstBallons {


  def maxCoins(nums: Array[Int]): Int = {

    if(nums.isEmpty) return 0

    val len = nums.length+2
    val iNum = Array.ofDim[Int](len)

    iNum(0) = 1
    iNum(len-1) =1

    val result = Array.fill(len, len)(0)

    for{
      i <- 0 to len-3
    }{
      iNum(i+1) = nums(i)
    }

    //k is len-1, right = left+k
    // region [left, left+k]
    for{
      k <- 2 until len
      left <- 0 until len - k
    }{
      val right = left + k
        for{
           j <- left + 1 until right
      }{

            result(left)(right) = Math.max(result(left)(right),
              iNum(left) * iNum(j) * iNum(right)  + result(left)(j) + result(j)(right)
              )
        }
    }

    result(0)(len-1)
  }


  def main(args: Array[String]): Unit = {

  /*  val test1 = maxCoins(Array(3))

    assert(test1==3, test1)*/


    val test2 = maxCoins(Array(3,1,5))

    assert(test2== 35, test2)

/*    val test3 = maxCoins(Array(3,1,5))

    assert(test3== 167, test3)*/
  }
}
