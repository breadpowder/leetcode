package question


/*
757. Set Intersection Size At Least Two
Hard

152

13

Favorite

Share
An integer interval [a, b] (for integers a < b) is a set of all consecutive integers from a to b, including a and b.

Find the minimum size of a set S such that for every integer interval A in intervals, the intersection of S with A has size at least 2.

Example 1:
Input: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]
Output: 3
Explanation:
Consider the set S = {2, 3, 4}.  For each interval, there are at least 2 elements from S in the interval.
Also, there isn't a smaller size set that fulfills the above condition.
Thus, we output the size of this set, which is 3.
Example 2:
Input: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]
Output: 5
Explanation:
An example of a minimum sized set is {1, 2, 3, 4, 5}.
Note:

intervals will have length in range [1, 3000].
intervals[i] will have length 2, representing some integer interval.
intervals[i][j] will be an integer in [0, 10^8].

 */

//TODO to review with all other interval problems, some conditions are trivial
object SetIntersectionSizeAtLeastTwo {

  import scala.collection.mutable.ArrayBuffer

  case class Interval(begin: Int, end: Int){
    override  def toString = begin + "," + end
  }

  object IntervalOrdering extends Ordering[Interval] {
    def compare(a:Interval, b:Interval) = {
      val comp = a.end compareTo b.end
      if(comp == 0 ) a.begin compareTo b.begin
      else comp

    }
  }


  def intersectionSizeTwo(intervals: Array[Array[Int]]): Int = {

    if(intervals.isEmpty) return 0

    else if(intervals.length==1) return 2


    val sorted: Array[Interval] = intervals.map(interval =>  Interval(interval(0), interval(1))).sorted(IntervalOrdering)

    val start = extractLastTwo(sorted.head)

    val arrayBuffer = ArrayBuffer[Int](start:_*)


   println(sorted.mkString("|"))
    sorted.tail.foldLeft(arrayBuffer){

      case(result: ArrayBuffer[Int], interval: Interval) => {

        val lastEnd = result(result.length-1)
        val lastBegin= result(result.length-2)

        //TODO merge (6,7) with interval (6,14), (7,14), (6,7)
       //TODO an error here, say (5,9) merge interval (8,9), (6,10) can not just do interval.begin== lastEnd
        if(interval.begin >= lastBegin && interval.begin <= lastEnd) {
          if(interval.begin == lastBegin && interval.end >= lastEnd)
          {
          }

          else if( interval.begin != lastBegin && interval.end == lastEnd ){
            //TODO if (11,18) merge inteval (16,18), the order should (17,18) nor (18,17)

            val toAppend = interval.end-1
            if(result.last > toAppend)
              {
                val last = result(result.length-1)
                result(result.length-1) = toAppend

                result.append(last)
              }

              else {
              result.append(toAppend)
              }
          }

          else{
            result.append(interval.end)
          }
        }

        else if(interval.begin > lastEnd){

          result.append(extractLastTwo(interval):_*)
        }

        println("Interval " + interval.begin + "," + interval.end)
        println("Result:" + arrayBuffer.mkString(","))
        result
      }

    }

    arrayBuffer.size
  }


  def extractLastTwo(interval: Interval) = Array(interval.end -1, interval.end)


  def main(args: Array[String]): Unit = {

/*
    val test1 = Array(Array(1, 2), Array(2, 3), Array(2, 4), Array(4, 5))

    assert(intersectionSizeTwo(test1)==5)

    val test2 = Array(Array(8,9),Array(4,21),Array(3,19),Array(5,9),Array(1,5))

    assert(intersectionSizeTwo(test2)==4)

    
    val test3 = Array(Array(4,14),Array(6,17),Array(7,14),Array(14,21),Array(4,7))
    assert(intersectionSizeTwo(test3)==4)*/

/*    val test5 = Array(Array(1,3), Array(1,2), Array(0,1))

    assert(intersectionSizeTwo(test5)==3)*/

/*    val test6 = Array(Array(2,3),Array(2,3),Array(0,1),Array(2,5),Array(2,4))

    assert(intersectionSizeTwo(test6)==4)*/
    
    
    val test7 = Array(Array(16,18),Array(11,18),Array(15,23),Array(1,16),Array(10,16),Array(6,19),Array(18,20),Array(7,19),Array(10,11),Array(11,23),Array(6,7),Array(23,25),Array(1,3),Array(7,12),Array(1,13),Array(23,25),Array(10,22),Array(23,25),Array(0,19),Array(0,13),Array(7,12),Array(14,19),Array(8,17),Array(7,23),Array(4,24))


    assert(intersectionSizeTwo(test7)==11)

  }

}
