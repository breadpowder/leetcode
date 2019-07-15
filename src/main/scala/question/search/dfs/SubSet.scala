package question.search.dfs
/*
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */
object SubSet {

    def subsets(nums: Array[Int]): List[List[Int]] = {

      //initiate result
      var result  = List.empty[List[Int]]

      def subsetsRec(n: Int, i: Int, s: Int, cur: Array[Int]): Unit = {

        if(n==0) {

          result = cur.toList :: result

        }

        else{

          for(idx <-  s until nums.length){
            val org = cur(i)

            cur(i) = nums(idx)
            subsetsRec(n-1, i+1, idx+1, cur)
            cur(i) = org
          }
        }

      }

      for(idx <- 1 to nums.length){
        subsetsRec(idx, 0, 0, new Array[Int](idx))
      }

      // remmeber Array.fill(idx)(0)
      List.empty[Int]::result
    }


}
