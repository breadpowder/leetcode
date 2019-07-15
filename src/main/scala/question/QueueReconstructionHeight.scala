package question

/*


406 Suppose you have a random list of people standing in a queue. Each person is
 described by a pair of integers (h, k), where h is the height of the person and
 k is the number of people in front of this person who have a height greater than or
  equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.


Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]*/



object QueueReconstructionHeight {

  def reconstructQueue(people: Array[Array[Int]]): Array[Array[Int]] = {

    val updated=  new Array[Array[Int]](people.length)

    //TOD DEEP COPY API !!!
    for(idx <- 0 until  people.length){
      val tmp = new Array[Int](2)
      people(idx).copyToArray(tmp)
      updated(idx) = tmp

    }


    for(idx <- 0 until people.length-1){
      findMin(idx)
    }


   def swap(arr: Array[Array[Int]], idx1: Int, idx2:Int) ={
     val tmp = arr(idx1)
     arr(idx1) = arr(idx2)
     arr(idx2) = tmp
   } 

  def findMin(fromIdx: Int)  ={

    var minIdx = fromIdx

    for{idx <- fromIdx+1 until updated.length
    //TODO conditional is not trivial, must find first element that is 0
    if (updated(minIdx)(1)!=0 && updated(idx)(1)==0) || (updated(idx)(1) ==0 && updated(idx)(0) <= updated(minIdx)(0))
    }{
       minIdx = idx
    }


    for{
      idx <- fromIdx until updated.length
      if idx!=minIdx && updated(idx)(0)<=updated(minIdx)(0)
    }{
      updated(idx)(1) -= 1
    }

    swap(updated, fromIdx, minIdx)
    swap(people, fromIdx, minIdx)
  }



      people
  }

  def main(args: Array[String]): Unit = {

    val result = reconstructQueue(Array(Array(7,0), Array(4,4), Array(7,1), Array(5,0), Array(6,1), Array(5,2)))

     result.map(a=>println(a.mkString(",")))

  }

}








