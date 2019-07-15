package question

import scala.collection.mutable


/*416. Partition Equal Subset Sum
Medium

901

23

Favorite

Share
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.*/
object partitionEqualProblem {

  def canPartitionTimeout(nums: Array[Int]): Boolean = {

    val sum = nums.sum

    val target = sum/2

    val table = mutable.HashMap[Int, Boolean]()


    //TODO only set up table when the nodeId is true, since different path can lead to true

    def wrongMethodcanFindSubsetTimeout(nums: Array[Int], start:Int, end:Int, target: Int) : Boolean= {

      if(end==0)
      {
        if(target == 0 || nums(0)==target) return true
        return  false

      }

      table.get(target) match {

        case Some(v) if v == true =>  return v
        case _ => {

          val works = wrongMethodcanFindSubsetTimeout(nums, start, end - 1, target) ||  wrongMethodcanFindSubsetTimeout(nums, start, end - 1, target - nums(end))

          table.put(target,works)


        }
      }

       return table.get(target).get


    }


    (sum & 1) == 0 && wrongMethodcanFindSubsetTimeout(nums,0, nums.length-1, target)

  }

  def canPartitionBottomUp(nums:Array[Int]): Boolean ={

    val sum = nums.sum

    if ((sum & 1)!=0) return false

    val target = sum/2

    //TODO keep all sumation state boolean in length * nodeId array

    val table = Array.ofDim[Boolean](nums.length+1, target+1)



    for(sigma <- 0 to target){
      table(nums.length)(sigma) = false
    }

    for(idx <- nums.length-1 to 0 by -1){

      for(sigma <- 0 to target)
        {

          table(idx)(sigma) = {

            if (table(idx+1)(sigma) ||sigma - nums(idx)==0 || (sigma-nums(idx)>0 && table(idx+1)(sigma- nums(idx))))
              true

            else
              false
          }
        }
      if(table(idx)(target)) return true
  }

    return  false

  }


  def canPartition(nums: Array[Int]): Boolean = {

    val sum = nums.sum

    val target = sum / 2

    val table = Array.ofDim[Int](nums.length, target+1)

    def canFindSubset(nums: Array[Int], start: Int, target: Int): Boolean = {

      if (target < 0 || start > nums.length - 1) {
        return false
      }

      if (target == 0) {
        table(start)(0) = 1
        return  true
      }


      if (table(start)(target)!=0) if(table(start)(target) ==1) true else false



      val possible = canFindSubset(nums, start + 1, target) || canFindSubset(nums, start+1, target - nums(start))

      table(start)(target) = if (possible) 1 else -1

      return  possible

    }

    (sum & 1) == 0  && canFindSubset(nums, 0, target)
  }




  def main(args: Array[String]): Unit = {

   // println(canPartition(Array(1, 5, 11, 5)))

    //println(canPartition(Array(1, 2, 3, 5)))

   // println(canPartition(Array(1,2,5)))


    TestUtility.time{println(canPartitionBottomUp(Array(100,100,100,100,100,100,100,100,100,100,100,100,100,
      100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,
      100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,
      100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,
      100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100)))}
  }

}
