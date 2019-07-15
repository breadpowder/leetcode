package question.bitwise

/*

260 single number iii

Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.


Example:

Input:  [1,2,1,3,2,5]
Output: [3,5]
Note:

The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?*/


object SingleNumber {

    def singleNumber(nums: Array[Int]): Array[Int] = {


      //why can't reduce here
     var xor=  nums.reduce(_^_)

      var xorSum = xor
      //find the first bit that differs
      var bit =1
      while((xor & 1) ==0){
        xor = xor >> 1
        bit = bit <<1
      }

      //split number to groups
      var oneGroup = 0
      for(num <- nums){
        if((num & bit) !=0)
          {
            oneGroup = num ^ oneGroup
          }

      }

      Array(oneGroup, xorSum ^ oneGroup)

    }

   def main(args: Array[String]): Unit = {

     val nums = Array(1,2,1,3,2,5)
     val result = singleNumber(nums)

     result.foreach(println)
   }
}
