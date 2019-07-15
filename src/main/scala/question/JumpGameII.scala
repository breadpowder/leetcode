package question

import scala.io.Source

/*

45. Jump Game II

Favorite

Share
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
Note:

You can assume that you can always reach the last index.
 */

object JumpGameII {

  //It's n square greedy algorithm

  def minStepsNSqaure(nums: Array[Int]): Int = {


    val len = nums.length

    for(idx <- 0 to nums.length-1){
      nums(idx) = idx + nums(idx)
    }
    var result :List[(Int, Int)] = List.empty

    //TODO check how to initial empty tuple2
    var lastStep:(Int, Int) = (len, len)
    var lastIdxToSearch = len -1


    while(lastIdxToSearch>0) {
      for {
        idx <- lastIdxToSearch to 0 by -1
        if nums(idx) >= lastIdxToSearch
      } {

        lastStep = (idx, lastIdxToSearch - idx)
      }

      result = lastStep +: result
      lastIdxToSearch = lastStep._1
    }

    //result.foreach(v=> println(s"start at ${v._1}, jump ${v._2} steps"))

    result.size
  }

  def countTime(nums:Array[Int])(f: Array[Int] => Int): Int ={

    val start = System.currentTimeMillis()

     val result = f(nums)


    val time = System.currentTimeMillis() - start

    println(s"took  $time ms, $result")
      result
  }



  //TODO duplicates, if an index was visited, don't need to visit again in following level
  //TODO BFS without revisit

  def minSteps(nums: Array[Int]): Int = {

    var count = 0

    var currentLevelRange = (0 to 0) toArray

    var nextLevelMax =0

    var nextLevelMin =0


    while (nextLevelMax < nums.length - 1) {

      //TODO find the maximum can reach, not each one
      nextLevelMin = currentLevelRange.last +1

     nextLevelMax = currentLevelRange.foldLeft(0)((maxhop, idx) => {
       val nextLevelIdx = idx + nums(idx)
       if (nextLevelIdx > maxhop) nextLevelIdx else maxhop
     }
     )

      currentLevelRange = (nextLevelMin to nextLevelMax).toArray
      count += 1
    }

    return count

  }


  def minStepshit(nums: Array[Int]): Int = {


    var count = 0

    var currentLevelIdx = Set(0)

    while(currentLevelIdx.max< nums.length-1)
    {

      val nextLevelIdx = currentLevelIdx.flatMap{idx =>
        (1 to nums(idx)).map(_ + idx)
      }.toSet

      currentLevelIdx = nextLevelIdx
      count +=1
    }


    return  count

  }

/*    def minStepsBFS(nums: Array[Int]): Int = {


      var count = 0

      var currentLevelIdx = Set(0)

      var currentIdx = 0

      while(currentLevelIdx.max< nums.length-1)
      {

        for {

           idx <-
        }
      }

      return  count

    }*/







  def main(args: Array[String]) : Unit ={

      //println(minSteps(Array(6,2,5,0,0,2,0,2,0,1,2)))

      val bufferedSource = Source.fromFile("jumpgameii.txt")
      for (line <- bufferedSource.getLines) {
        countTime(line.split(",").map(_.toInt))(minSteps)
        countTime(line.split(",").map(_.toInt))(minStepsNSqaure)
      }


      ///assert(minSteps(Array(6,2,5,0,0,2,0,2,0,1,2))==4)


    }

}
