package question.backtracking

import scala.collection.mutable.ArrayBuffer

object Combinations {

  def combine(n: Int, k: Int): List[List[Int]] = {


    val arr = 1 to n toSet
    //scala.collection.mutable.HashSet(1 to n :_*)

    //TODO use this list buffer to append
    //TODO USE treeset to keep the order, seems it's very slow

    var results = new scala.collection.mutable.ListBuffer[List[Int]]




    def recursive(remain:Int, remaining:Set[Int] , result:ArrayBuffer[Int]=new ArrayBuffer[Int]): Unit = {

      remain match {
        case 0 => results.append(result.toList)


        case _ => {
          /*              remaining.foreach { v=>
                if(result.isEmpty || v > result.last) {
                  recursive(remain - 1, remaining - v, result + v)
                }
              }*/

          remaining.foreach { v =>
            if (result.isEmpty || v > result.last) {
              recursive(remain - 1, remaining - v , result :+ v )
            }
          }
        }
      }


    }



    recursive(k, arr)
    results.toList

  }






  def main(args: Array[String]): Unit ={
          combine(4,2).map(v=> v.mkString(",")).foreach(println)
  }
}
