package question.bitwise

//TODO redo this algorithm since i read the answer

/*

137
Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,3,2]
Output: 3
Example 2:

Input: [0,1,0,1,0,1,99]
Output: 99

 */
object SingleNumberII {

  def singleNumber(nums: Array[Int]): Int = {

    var result = 0

    for (i <- 0 to 31) {

      var sum = 0
      val marker = 1 << i

      for (idx <- 0 to nums.length - 1) {


        //TODO why this is wrong ((nums(idx) & marker)) >> i == 1 signed right shift, . first or most significant bit never lost, doesn't matter how many times you shift
        if ((((nums(idx) & marker)) >>> i )== 1) {
          sum += 1
        }

      }

        sum = sum % 3

        if (sum == 1) {
          result |= marker
        }
      }



      result


  }


  def main(args: Array[String]): Unit={

    var arr = Array(0,1,0,1,0,1,99)

   assert(singleNumber(arr) == 99)

    arr = Array(1,1,1,3)

    assert(singleNumber(arr) ==3)

    arr = Array(-1,-1,-1,3)
    assert(singleNumber(arr) == 3)


    arr = Array(-2,-2,1,1,-3,1,-3,-3,-4,-2)
    assert(singleNumber(arr) == -4)


  }
}
