package question

import scala.collection.mutable.ListBuffer

/*
18
Given an array nums of n integers and an integer nodeId, are there elements a, b, c, and d in nums such that a + b + c + d = nodeId? Find all unique quadruplets in the array which gives the sum of nodeId.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and nodeId = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
 */



object FourSum {

/*  import scala.util.Sorting.quickSort

  val a = Array(1, 2, 3)
  quickSort[Int](a)(Ordering[Int].reverse)*/


  //       ^---^ the most important bit

  def fourSum(nums: Array[Int], target: Int): List[List[Int]] = {


    scala.util.Sorting.quickSort(nums)

    implicit  def swap(arr:Array[Int], i:Int, j:Int) = {

      var tmp = arr(i)
      arr(i) = arr(j)
      arr(j) = tmp
    }

    def quickSortConcise(arr: Array[Int], start: Int, end: Int)(implicit swap: (Array[Int],Int, Int) => Unit): Array[Int] = {


      def partition(low: Int, high: Int) :Int = {

            val pivot = arr(high)

            var next = low

            for{
              start <- low to high
              if(arr(start)<pivot)
            } {
              swap(arr,start,next)
              next +=1
            }


          swap(arr,next, high)

          next

      }

      if(start>=end) {return arr}

      val pivot = partition(start,end)
      quickSortConcise(arr,start, pivot-1)
      quickSortConcise(arr,pivot+1, end)

    }


    def twoSum(sortedArr:Array[Int]): List[List[Int]] = {

      var low = 0
      var high = sortedArr.length-1

      var listBuffer: ListBuffer[List[Int]] = ListBuffer.empty


      while(low<=high){

        val valLow = sortedArr(low)
        val valHigh = sortedArr(high)

        if(valLow + valHigh == target)
          {
            listBuffer.append(List(valLow,valHigh))
            low +=1
            high -=1
          }


        else if  (valLow + valHigh < target)
          low +=1
        else
          high -=1

      }

      listBuffer.toList
    }


    //TODO: step1 build n squre sum and sum->list(tuple) step 2, sort sum step 3. build count map for array

   val numToFreq = nums.groupBy(n=>n).mapValues(v => v.length)

    val sumToTuples = for{
      i <-0 to nums.length-1
      j <- i+1 to nums.length-1
    } yield {

      if (nums(i) < nums(j))
      (nums(i) + nums(j), (i,j))

      else
        (nums(i)+ nums(j), (j,i))
    }

   val sumToTuplesMap= sumToTuples.groupBy(_._1).mapValues(v => v.map(_._2))

   val pairSum=  sumToTuplesMap.keys.toArray

    val sortedPair = quickSortConcise(pairSum,0, pairSum.length-1)

    val sumValues = twoSum(sortedPair)

    val result = sumValues.flatMap{

      list => list match {

        case List(first, last) => {

          val firstPairs = sumToTuplesMap.get(first)

          val secondPairs = sumToTuplesMap.get(last)

          for{ firstPair <- firstPairs.get

              secondPair <- secondPairs.get
          } yield (firstPair._1, firstPair._2, secondPair._1, secondPair._2)


        }
      }

    }.filter(
       t  => t._1!=t._3 && t._1!=t._4 && t._2!=t._3 && t._2!=t._4
    ).map(t => List(nums(t._1),nums(t._2),nums(t._3),nums(t._4)).sorted).toSet.toList

    result.map(println)
    result


  }


  def main(args: Array[String]): Unit ={
 var arr= Array(1, 0, -1, 0, -2, 2)

    fourSum(arr,0)


     arr =Array(0,0,0,0)

    assert(fourSum(arr,0).length==1)
  }
}
