package question


/*
452. Minimum Number of Arrows to Burst Balloons
There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice. Start is always smaller than end. There will be at most 104 balloons.

An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely. The problem is to find the minimum number of arrows that must be shot to burst all balloons.

Example:

Input:
[[10,16], [2,8], [1,6], [7,12]]

Output:
2

Explanation:
One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
 */
object MinArrowShots {


  def findMinArrowShots(points: Array[Array[Int]]): Int = {


    var sortedPoints = points.sortWith((a,b) => a(1)<b(1))

    //sortedPoints.foreach(arr=> println(arr.mkString(",")))


      var count =0

      while(sortedPoints nonEmpty) {
        val head =sortedPoints.apply(0).apply(1)
        sortedPoints = sortedPoints.filter(point => point(0) > head)
        count +=1
      }


    count
  }


  def main(args:Array[String]) : Unit={

    val arr = Array(Array(10,16), Array(2,8), Array(1,6), Array(7,12))

    println(findMinArrowShots(arr))
  }
}
