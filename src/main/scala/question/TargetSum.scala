package question


/*
494. Target Sum
 You are given a list of non-negative integers, a1, a2, ..., an, and a nodeId, S. Now you have 2 symbols + and -. For each integer, you
 should choose one from + and - as its new symbol.

  Find out how many ways to assign symbols to make sum of integers equal to nodeId S.

    Example 1:
  Input: nums is [1, 1, 1, 1, 1], S is 3.
  Output: 5
  Explanation:

    -1+1+1+1+1 = 3
  +1-1+1+1+1 = 3
  +1+1-1+1+1 = 3
  +1+1+1-1+1 = 3
  +1+1+1+1-1 = 3

  There are 5 ways to assign symbols to make the sum of nums be nodeId 3.
 */


/* 0 is a special case, 1,0, Comment from leecode
The reason for top-down iterative is: we cannot pick the
 same element more than once, top-down would prevent this.
 For example, start with picking num, dp[num] = 1 since there is one way to reach num (i.e., just picking num). if num is only allowed to be picked once, all the other elements in dp[] except dp[num] should be 0. This can be achieved if the loop is top-down from P to num. If it is bottom-up, we know dp[num] = 1, then dp[num+num] = 1, (num is picked twice), dp[num+num+num] = 1 (num is picked three times) etc. So, if a number is allowed to be picked for more than once, we use bottom-up iteration.
*/
object TargetSum {


  //Set is wrong, e.g. a + b -c a-b+c are two
  def findTargetSumWaysWrong(nums: Array[Int], S: Int): Int = {

    if(nums.isEmpty) return 0

    var result = new collection.mutable.HashSet[Int]()

    result.add(nums(0))

    for{
       num <- nums
    }{

      var tmpSum = List.empty[Int]
      for {
         sum <-result
      }{
        tmpSum =   (sum - num) +: (sum + num) +: tmpSum
      }

      result.clear
      result = result ++ tmpSum


    }

    result.filter(_==S).size

  }

  def findTargetSumWays(nums: Array[Int], S: Int): Int = {

    if(nums.isEmpty) return 0

    val result = new collection.mutable.Queue[Int]()

    result.enqueue(nums(0),-nums(0))

    for{
      idx <-  1 to nums.length-1
    }{

      var tmpSum = List.empty[Int]
      while(result.nonEmpty){
        val sum = result.dequeue()
        tmpSum =   (sum - nums(idx)) +: (sum + nums(idx)) +: tmpSum
      }

      result.enqueue(tmpSum: _*)

      //println(result.size)
    }

    result.filter(_==S).size

  }


  // A + B =SUM, A-B = S, A= (SUM +S)/2
  def findTarget(nums: Array[Int], S: Int): Int = {

    if(nums.isEmpty) return 0

    val sum = nums.sum
    if(S>sum) return 0


    def findTargetBottomUp(target: Int): Int = {


      val table = Array.fill[Int](nums.length + 1, target + 1)(0)
      //init

      val lastIdx = nums.length


      table(lastIdx)(0) = 1

 /*     for (idx <- 0 to lastIdx) {
        table(idx)(0) = 1
      }*/

      //table(lastIdx)(nums(lastIdx)) = 1

      for {
        idx <- nums.length - 1 to 0 by -1
        sum <- 0 to target
      } {


        table(idx)(sum) +=  table(idx + 1)(sum)

        if(sum- nums(idx)>=0)
          table(idx)(sum) += table(idx + 1)(sum - nums(idx))
       // if(sum-nums(idx)==0) table(idx)(sum) = table(idx)(sum) + 1

      }


      table(0)(target)


      /*    val dp = new Array[Int](nodeId + 1)
      dp(0) = 1
      for (n <- nums) {
        for {i <- nodeId to n by -1}
          dp(i) += dp(i - n)
      }

      dp(nodeId)
    }
*/
    }
    val total = nums.sum + S

    if ((total & 1) == 1) 0

    else findTargetBottomUp(total>>>1)


  }


  def main(args: Array[String]): Unit = {

    var arr =Array(1,1,1,1,1)

    println(findTarget(arr,3))



    arr = Array(1000)

    println(findTarget(arr, -1000))


    arr = Array(1,0)

    println(findTarget(arr,  1))


    arr = Array(35,25,24,23,2,47,39,22,3,7,11,26,6,30,5,34,10,43,41,28)

    //Array(42,16,31,11,36,19,9,3,25,0,27,29,35,29,45,15,35,42,35,23)

    System.out.println(TestUtility.time(println(findTargetSumWays(arr,49))))


  }
}
