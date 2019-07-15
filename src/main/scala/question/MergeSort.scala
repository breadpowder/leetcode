package question

import scala.annotation.tailrec

object MergeSort {


  def mergeSort(arr: List[Int]): List[Int] ={


    def merge(left: List[Int], right:List[Int]): List[Int] = {
      var merged: List[Int] = List.empty

      var indexLeft, indexRight: Int = 0


      while (indexLeft < left.size && indexRight < right.size) {

        val next = {
          if (left(indexLeft) < left(indexRight)) {
            indexLeft += 1
            left(indexLeft)

          }

          else {
            indexRight += 1
            right(indexRight)

          }
        }
        merged = merged ::: List(next)

      }


      if (indexLeft >= left.size && indexRight < left.size) {
        return merged ::: right.slice(indexRight, right.size)
      }

      else if (indexRight >= right.size && indexLeft < left.size) {
        return merged ::: left.slice(indexLeft, left.size)

      }
      else{
        return merged
      }
    }


    if(arr.isEmpty || arr.size == 1)  {
      return arr
    }

    val mid = (arr.size  -1 )/ 2

    val left = mergeSort(arr.slice(0, mid))

    val right = mergeSort(arr.slice(mid+1, arr.size))

    merge(left, right)

  }


  implicit def intcompare(x:Int, y:Int) = x<y

  def mergeSortTailR(arr: List[Int])(implicit pred: (Int,Int) => Boolean): List[Int] = {

    @tailrec
    def mergeTailR(left: List[Int],right:List[Int], acc: List[Int] = List.empty): List[Int] = (left, right) match {
      case (Nil, _) => acc ++ right

      case (_, Nil) => acc ++ left

      case (l :: left1, r :: right1) => {
        if (pred(l, r)) mergeTailR(left1, right, acc :+ l)
        else mergeTailR(left, right1, acc :+ r)

      }
    }

    if(arr.size/2==0) return arr



    else {
         val mid = arr.size/2

         val (left, right) = arr splitAt mid

         mergeTailR(mergeSortTailR(left), mergeSortTailR(right))
    }


  }

  def main(args: Array[String]): Unit = {

    //val result = mergeSort(List(6,3,5,9,4))
    val result = mergeSortTailR(List(6,3,5,9,4))

    println(result.mkString(","))

    assert(result.sameElements(List(3,4,5,6,9)))

  }






}
