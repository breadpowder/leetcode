package question


/*
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */

//TODO pattern match an empty array and variable array
object MaximumProductSubarray {

  def maxProduct(nums: Array[Int]): Int = {

    def multiples(arr: Array[Int], start:Int, end:Int): Int = {

      if(start>=end) return arr(end)

      var multiply = 1
      for{
        idx <- start to end
      }{
        multiply = multiply * arr(idx)
      }
      multiply
    }


    def maxiumProductSubarray(subArr: Array[Int]) : Int={

      val negativeIdx = subArr.zipWithIndex.filter(_._1<0).map(_._2)


      if (negativeIdx.length %2 ==0) {
        multiples(subArr,0,subArr.length-1)
      }

      else{
        val first=  negativeIdx.apply(0)

        val last = negativeIdx.apply(negativeIdx.length-1)

        val leftMultiples = {

/*          val left =subArr.drop(first+1)

          left match {
            case Array() => nums(first)
            case arr @ Array(a,_*) => multiples(arr)
          }*/

          multiples(subArr, Math.min(first+1, subArr.length-1), subArr.length-1)
        }

        val rightMultiples ={
  /*        subArr.dropRight(subArr.length-last) match  {
            case Array() => nums(first)
            case arr @ Array(a, _*) => multiples(arr)
          }*/

          multiples(subArr,0, Math.max(last-1, 0))

        }

        Math.max(leftMultiples, rightMultiples)

      }
    }

    var segments: List[Array[Int]] = List.empty[Array[Int]]

    var max = nums(0)

    var segment : Array[Int] = Array[Int]()

    for {
          num <- nums
        }{

        num match{
          case 0 => {
            segments =  segment :: segments
            segment = Array[Int]()
          }
          case _ =>  segment = num +: segment

        }

        if(max < num) max = num
     }

    segments = segment:: segments

    for(segment <- segments){
      segment match {
        case Array() =>

        case _=> {
          val product = maxiumProductSubarray(segment)
          if(product>max) max =product
        }
      }

    }
    max
  }


  def maxProductEasy(nums: Array[Int]) = {

    var max = nums(0)

    var min = nums(0)

    var result = 0

    (1 to nums.length -1).foreach{
      idx =>

        max = Math.max(Math.max(max* nums(idx), min*nums(idx)), nums(idx))

        min = Math.min(Math.min(max*nums(idx), min*nums(idx)), nums(idx))

        result = Math.max(result, max)

    }

    result
  }


  def main(args: Array[String]): Unit = {


    var nums =Array(2,3,-2,4)

    println(maxProduct(nums))
    println(maxProductEasy(nums))



    nums = Array(-2,0,-1)
    println(maxProduct(nums))
    println(maxProductEasy(nums))



    nums = Array(1,2, -7,-3, 0,2,3 , 0, 4,5)
    println(maxProduct(nums))
    println(maxProductEasy(nums))






  }
}
