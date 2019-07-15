package question.heap

object FindKPairswithSmallestSums {


  case class Item(v: Int, n1: Int, n2: Int)


  case object ItemOrdering extends Ordering[Item]
  {

    override def compare(x: Item, y: Item): Int = y.v.compareTo(x.v)

  }


  def kSmallestPairs(nums1: Array[Int], nums2: Array[Int], k: Int): List[Array[Int]] = {
    var result = List.empty[Array[Int]]


    if(nums1.isEmpty || nums2.isEmpty) return result

    //steps: see matrix (i,j) takes i from nums1 and j from nums2, heap to handle


    val (height: Int, width: Int) = (nums1.length, nums2.length)

    //if(k>= height * width)  return Array(nums1(height-1), nums2(width-1))::result


    val minHeap = collection.mutable.PriorityQueue.empty[Item](ItemOrdering)

    val num1_min = nums1.head

    //initialize
    nums2.zipWithIndex.foreach { tuple =>
      minHeap.enqueue(Item(num1_min + tuple._1, 0, tuple._2))
    }

    var count = 0

    while (count < k && count< width * height) {

      val min = minHeap.dequeue()

      val idxFromNum1 = min.n1
      val idxFromNum2 = min.n2

      result = Array(nums1(idxFromNum1), nums2(idxFromNum2)) :: result

      if(idxFromNum1<height-1){
        minHeap.enqueue(Item(nums1(idxFromNum1+1)+nums2(idxFromNum2),idxFromNum1+1, idxFromNum2))
      }

      count += 1
    }

    result.reverse

  }


  def main(args: Array[String]): Unit = {

    val test1_num1 = Array(1,1,2)
    val test1_num2= Array(1,2,3)
    val test1_result = kSmallestPairs(test1_num1, test1_num2,2)

    List(Array(1,1),Array(1,1)).zip(test1_result).foreach{
      tuple => assert(tuple._1.sameElements(tuple._2))
    }



    val test2_num1 = Array(1,2)
    val test2_num2 = Array(3)

    val test2_result = kSmallestPairs(test2_num1, test2_num2,3)


    println( test2_result.map(_.mkString(",")).mkString("|"))

    List(Array(1,3),Array(2,3)).zip(test2_result).foreach{
      tuple => assert(tuple._1.sameElements(tuple._2))
    }



    //assert( test2_result == List(Array(1,3),Array(2,3)), test2_result.map(_.mkString(",")).mkString("|"))

  }
}
