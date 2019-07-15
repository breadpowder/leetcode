package question

object SlidingWindowMaximum {

/*
219 sliding window maximum

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.

You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

    Example:

    Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
  Output: [3,3,5,5,6,7]
  Explanation:

    Window position                Max
  ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
  1 [3  -1  -3] 5  3  6  7       3
  1  3 [-1  -3  5] 3  6  7       5
  1  3  -1 [-3  5  3] 6  7       5
  1  3  -1  -3 [5  3  6] 7       6
  1  3  -1  -3  5 [3  6  7]      7*/

  def maxSlidingWindow(nums: Array[Int], k: Int): Array[Int] = {


    if(nums.isEmpty) return Array.empty[Int]

    val window = new java.util.ArrayDeque[Int](1000)

    def addToVector(num: Int): Unit ={

       while(!window.isEmpty && num> window.getLast) {
         window.removeLast()
       }

      window.addLast(num)
    }

    for{

      idx <- 0 until k-1
    } {
      addToVector(nums(idx))
    }

    val result = Array.ofDim[Int](nums.length- k +1)

    for{
      idx <- 0 to nums.length -k
    }{

      addToVector(nums(idx + k -1))
      result(idx) = window.getFirst
      if(nums(idx) == result(idx)) {
          window.removeFirst()
        }
    }

    result


  }

  def main(args: Array[String]): Unit = {

/*    var testArr : Array[Int] = Array(1,2,3)

    TestUtility.time(maxSlidingWindow(testArr,3))*/

    val inputs = TestUtility.readFile("slidingWindowInput.txt")
    val arr = inputs(0).split(",").map(_.toInt)

    TestUtility.time(maxSlidingWindow(arr , inputs(1).toInt))

    println(maxSlidingWindow(arr, inputs(1).toInt).mkString(","))

/*    maxSlidingWindow(Array(1,-1),1).foreach(println)

    maxSlidingWindow(Array(7,2,4),2).foreach(println)

    maxSlidingWindow(Array(1,3,-1,-3,5,3,6,7),3).foreach(println)

    assert(maxSlidingWindow(Array(1,3,-1,-3,5,3,6,7),3).sameElements(Array(3,3,5,5,6,7)))*/
  }
}
