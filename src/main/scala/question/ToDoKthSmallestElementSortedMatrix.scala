
package question

/*

378. Kth Smallest Element in a Sorted Matrix


Share
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
 */

//TODO most import thing, why priority queue method works

object ToDoKthSmallestElementSortedMatrix {


  case class Item(v: Int, r: Int, c: Int)


  case object ItemOrdering extends Ordering[Item]
  {

    override def compare(x: Item, y: Item): Int = y.v.compareTo(x.v)

  }

  def kthSmallest(matrix: Array[Array[Int]], k: Int): Int = {


    val height = matrix.length

    val width = matrix.head.length

    val minHeap = collection.mutable.PriorityQueue.empty[Item](ItemOrdering)


    matrix.head.zipWithIndex.foreach { tuple =>
      minHeap.enqueue(Item(tuple._1, 0, tuple._2))
    }


    var count = 1
    var nextRow, nextCol = 0

    //TODO condition! condition and AND or  does not work   while (count < k  && (nextRow!= height-1 || nextCol != width-1)) {
    // TODO take special care of the last line, need to think more
    //TODO NOT work must stop enqueue when the last element has been enqued, otherwise, the last row enqueus more than once

    while (count < k) {

       val item = minHeap.dequeue()
       val (row,col)  = (item.r, item.c)
       nextRow = Math.min(row+1, height-1)
       nextCol = col
      if(row < height-1) {
        minHeap.enqueue(Item(matrix(nextRow)(nextCol), nextRow, nextCol))
      }
       count +=1
    }

/*
    for(i <- 1 to k-count) {
      minHeap.dequeue()
    }*/

    minHeap.dequeue().v
  }




  def main(args: Array[String]): Unit = {

   def createTest1 = {
      val line1 = Array(1, 5, 9)

      val line2 = Array(10, 11, 13)

      val line3 = Array(12, 13, 15)

      Array(line1, line2, line3)
    }

   val test1 =  createTest1

    assert(kthSmallest(createTest1, 2)==5, kthSmallest(createTest1,2))

    assert(kthSmallest(createTest1, 8)==13, kthSmallest(createTest1,8))

   val test2 = Array(Array(1,2), Array(1,3))

    assert(kthSmallest(test2, 3)==2, kthSmallest(test2,3))

    val test3 =Array(Array(1,2,3,4,5),Array(6,7,8,9,10),
      Array(11,12,13,14,15),Array(16,17,18,19,20),Array(21,22,23,24,25))

    assert(kthSmallest(test3, 25)==25, kthSmallest(test3,25))



    val test4 = Array(Array(4,4,4), Array(9,9,9),Array(9,14,15))
    assert(kthSmallest(test4, 8)==14, kthSmallest(test4,8))

  }
}

