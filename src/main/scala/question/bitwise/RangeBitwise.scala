package question.bitwise

/*
#201
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

Example 1:

Input: [5,7]
Output: 4
Example 2:

Input: [0,1]
Output: 0
 */


/// 11110101011
/// 11110111100
object RangeBitWise {
    def rangeBitwiseAnd(m: Int, n: Int): Int = {

      if (m!=n) {
        return rangeBitwiseAnd(m>>1, n>>1) << 1
      }
      else{
        m
      }

    }

    def main(args: Array[String]): Unit = {

      println(rangeBitwiseAnd(5,7))
      println(rangeBitwiseAnd(0,0))

    }
}
