package question

/*
560
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

 */


/*

     def subarraySum(nums: Array[Int], k: Int): Int =  { var sum: Int = 0
var result: Int = 0
val preSum = new HashMap[Int,Int]
preSum.put(0, 1)
var i: Int = 0
while ( { i < nums.length})  { sum += nums(i)
if (preSum.containsKey(sum - k))  { result += preSum.get(sum - k)
}
preSum.put(sum, preSum.getOrDefault(sum, 0) + 1)

{i += 1; i - 1}}
return result
}

 */

//TODO optimization ordering is important, i < j, when we approach j we have computed i
object SubArraySum {


  def subarraySums(nums: Array[Int], k: Int) :Int ={

    if(nums.isEmpty) return 0

    val sumHashMap = new collection.mutable.LinkedHashMap[Int,List[Int]]()

    val prefixSum = Array.ofDim[Int](nums.length+1)

    var sum = 0
    for(idx <-0 to nums.length-1){
      sum = sum + nums(idx)
      prefixSum(idx+1) = sum
      sumHashMap.get(sum) match {
        case Some(v) => sumHashMap.put(sum,idx::v)
        case _ => sumHashMap.put(sum,List(idx))
      }
    }


    var total = 0
    for( idx <-0 to prefixSum.length-1){

      val preSum = prefixSum(idx)
      val target = preSum + k

      if(sumHashMap.contains(target)){
       total = total + sumHashMap.get(target).get.filter(_>idx-1).size
      }

    }

    total
  }

  def main(args: Array[String]): Unit = {

    var arr = Array(1)

    println(subarraySums(arr,0))

    arr = Array(1,1,1)

    println(subarraySums(arr,2))

    arr = Array(1,3,4,2,5,2,5)

    println(subarraySums(arr,7))

    arr = Array(3,4,-5,5,1)

    println(subarraySums(arr,5))

  }
}
