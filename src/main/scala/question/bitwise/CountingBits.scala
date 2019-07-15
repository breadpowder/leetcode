package question.bitwise

/*

Given a non negative integer number num. For every numbers i in the
range 0 ≤ i ≤ num calculate the number of 1's in their binary
representation and return them as an array.

Example 1:

Input: 2
Output: [0,1,1]
Example 2:

Input: 5
Output: [0,1,1,2,1,2]
 */


object CountingBits {

  def countBits(num: Int): Array[Int] = {

        val result = Array.fill(num+1)(0)

        for{i <- 1 to num}{

            result(i) = (i & 1) + result(i >> 1)
        }

    result

  }


  def main(args: Array[String]): Unit = {
    assert(countBits(2)  sameElements(Array(0,1,1)), countBits(2).mkString(","))
    assert(countBits(5)  sameElements(Array(0,1,1,2,1,2)))



  }
}
