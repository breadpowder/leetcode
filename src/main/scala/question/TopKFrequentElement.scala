package question


import scala.collection.mutable.ArrayBuffer

/*
347. Top K Frequent Elements
Medium

1213

79

Favorite

Share
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

 */

object TopKFrequentElement {

  def topKFrequent(nums: Array[Int], k: Int): List[Int] = {

    val numToFrequencyMap: Map[Int,Int] = nums.groupBy(n => n).map(tuple => (tuple._1, tuple._2.length))

    var treeMap =  collection.immutable.TreeMap.empty[Int,ArrayBuffer[Int]](Ordering.Int.reverse)

    for (elem<-numToFrequencyMap){
      val nums = treeMap.getOrElse(elem._2, new ArrayBuffer)
      nums.append(elem._1)
      treeMap= treeMap.updated(elem._2,nums)
    }

    //TODO why this group by not work as expected when (1,3),(2,3), it drops
    //numToFrequencyMap.map(tuple => (tuple._2, tuple._1)).groupBy(_._1).mapValues(_.map(_._2).toArray)




    var total = 0
    var result:ArrayBuffer[Int] = new ArrayBuffer[Int]()


    for{
      elem <-treeMap
      if total<k
    }
      {
        val list = elem._2
        total = total+list.size
        if(total<=k)
          result =  result ++ list
        else
          result = result ++ list.slice(0,k-(total-list.size))

      }
    result.toList
  }

  def main(args: Array[String]): Unit = {

   assert(topKFrequent(Array(1,1,1,2,2,3),2).sameElements(List(1,2)),topKFrequent(Array(1,1,1,2,2,3),2).mkString("|"))
   assert(topKFrequent(Array(2,2,2,1,1,1, 3),2).sameElements(List(2,1)),topKFrequent(Array(2,2,2,1,1,1,3),2).mkString("|"))


  }
}
