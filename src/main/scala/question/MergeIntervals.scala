package question

import java.util

import scala.annotation.tailrec

/**
  * Definition for an interval.**/
   class Interval(var _start: Int = 0, var _end: Int = 0) {
    var start: Int = _start
     var end: Int = _end
  }


object MergeIntervals {


  def merge(intervals: List[Interval]): List[Interval] = {



    object IntervalOrdering extends Ordering[Interval] {
      def compare(a:Interval, b:Interval) = {
        a.start compareTo b.start
      }
    }

    val sortedIntervals = intervals.sorted(IntervalOrdering)



    //intervals.sortWith((a:Interval,b:Interval)=>a.start <= b.start)

    @tailrec
    def mergeNext(intervals: List[Interval], previous: List[Interval]=List.empty): List[Interval] =
    {

      (previous,intervals) match {


        case (Nil, Nil)  =>  List.empty
        case (Nil, head :: tail) => mergeNext(tail, List(head))

        case (List(_*), Nil) => previous ::: intervals

        case (xs :+ head , next :: rest )=> {

          //TODO pattern match one element List(_),
          // more than one element List(a,_,_*), match last (xs :+ y)

          //TODO head::mergeNext(rest) is not tailrec

          //TODO recusrsion order if tail recur, should merge first and call recusrivion

          //TODO wired issue on sortwith with default comparision impliact comparasion, the error msg is break comparsion contract


          val head = previous.last
          //TODO overlap
          if(head.end>= next.start )
          {

            //can merge
            if(next.end> head.end)
            {
              mergeNext(rest, xs :+ new Interval(head.start,next.end))
            }

            else{
              mergeNext(rest,  xs :+ new Interval(head.start, head.end))
            }
          }

          else{
            mergeNext(rest, xs :+ head :+ next)
          }
        }
      }
    }

    def mergeNextNoneTail(intervals: List[Interval]): List[Interval] =
    {

      intervals match {


        case Nil  =>  intervals
        case List(_) => intervals
        case head :: next :: rest =>



          //TODO pass in merged result
          if(head.end>= next.start )
          {

            // plesae
            if(next.end> head.end)
            {
              mergeNextNoneTail(new Interval(head.start,next.end) ::rest)
            }

            else{
             mergeNextNoneTail( new Interval(head.start, head.end)::rest)
            }
          }

          else{
             head :: mergeNextNoneTail(next :: rest)
          }
        }
      }



    //mergeNext(sortedIntervals)
      mergeNextNoneTail(sortedIntervals)
}


  def main(args: Array[String]): Unit = {

    var intervals = List(new Interval(3, 10),
      new Interval(11, 14),
      new Interval(3, 9),
      new Interval(5,9)
     )
  /*    new Interval(8, 11),
      new Interval(15, 16),
      new Interval(5,8),
      new Interval(5,7),
    new Interval(5,9),
    new Interval(5,8))
*/


    merge(intervals).foreach(i => println(i.start + "," + i.end))

  }




}
