package question.backtracking

/*
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
 */

object CombinationSumIII {

  def combinationSum3(k: Int, n: Int): List[List[Int]] = {

    val nums = (9 to 1 by -1).toArray

    var result = List.empty[List[Int]]

    def combinationSumRecursive(remain_k: Int, target: Int, s: Int, cur: List[Int]): Unit ={

      if(target==0 && remain_k ==0 ){
        result = cur :: result
      }


      //TODO cut branch from low to large
      //TODO cut branch is remain==0
      else if(remain_k==0){
        return
      }

      else {
        for {
          idx <- 8  to s by -1
        } {
          if (nums(idx) > target) {
            return
          }
          //TODO pay attention it is idx+1 not s+1
          combinationSumRecursive(remain_k - 1, target - nums(idx), idx +1, nums(idx) :: cur)
        }
      }
    }

    combinationSumRecursive(k, n, 0, List.empty)

    result

  }

  def main(args: Array[String]): Unit = {
    val result = combinationSum3(3, 9)
    result.foreach(r => println(r.mkString(",")))
  }

}
