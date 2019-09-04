package question.divideAndConquer.others

//https://www.youtube.com/watch?v=KB9IcSCDQ9k&t=825s
// see two things: one is m1+ m2 =  (n1+n2+ 1)/2, the other is the out of boundry condition m1, m2
object MedianOfTwoSortedArray {



  def getMedian(arr: Array[Int], start:Int, end:Int): Int ={

      val idx=(start + end)/2
      arr(idx)


  }

 def isEven(nums1: Array[Int], nums2: Array[Int]) = (nums1.length + nums2.length) % 2 == 0



  def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {

      //TODO get median of num1 and num2
      //TODO a recursion to find index in two arrays,

      def findMedianRecurisve(start1:Int, end1:Int, start2:Int, end2:Int, idx: Int):Double = {

        if(idx<=0)
        {
          return Math.min(nums1(start1),nums2(start2))
        }

/*

        if (end1 - start1 < 2 || end2 - start2 < 2) {
          val (shortArr, longArr, start, end) = if (end1 - start1 < end2 - start2) (nums1, nums2, start1, end1) else (nums2, nums1, start2, end2)

          var result = Array.empty[Int]

          val num = shortArr.apply(start)
          result = Array.concat(longArr.filter(_ < num), Array(num), longArr.filter(_ == num), longArr.filter(_ >= num))


          return result(idx)

        }
*/

        val arr1Median = getMedian(nums1,start1, end1)
        val arr2Median = getMedian(nums2, start2, end2)

        //TODO pay attention to new start and idx -,  newStartIdx is  a new index while idx is for new rank



        if(arr1Median>arr2Median){

          val newStart2 = (start2+end2)/2

          val newStart1 = (start1 + end1)/2
          findMedianRecurisve(start1, end1, newStart2, end2, idx-(newStart2-start2))
        }

        else  if(arr1Median< arr2Median)
        {
          val newStart = (start1+end1)/2
          findMedianRecurisve(newStart, end1, start2, end2,idx-(newStart-start1))

        }

        else
          findMedianRecurisve(start1, (start1+end1)/2, start2, (start2+end2)/2,idx)
      }

    if(!isEven(nums1,nums2))
      {
        findMedianRecurisve(0, nums1.length-1, 0, nums2.length-1, (nums1.length + nums2.length -1)/2)
      }

    else{
      val left = findMedianRecurisve(0, nums1.length-1, 0, nums2.length-1, (nums1.length + nums2.length -1)/2)
      val right = findMedianRecurisve(0, nums1.length-1, 0, nums2.length-1, (nums1.length + nums2.length -1)/2 + 1)

      (left + right)/2.0
    }


  }


  def medianOfMedian(num1: Array[Int],num2:Array[Int]): Double ={


    //trival case
    // if (a.isEmpty)
    //1. get m1 and m2 = k -m1, CompareTest a(m1) b(m2)


    //2. find left median
    val (a,b) = if (num1.length < num2.length) (num1, num2) else (num2, num1)

    val k = (a.length + b.length-1)/2

    val totalLen = a.length + b.length



    if(a.length==0) {

      if((totalLen & 1) ==1) return b(k)

      else return ((b(k) + b(k+1))).toDouble/2

    }

    def medianOfMedianRec(low: Int, high:Int ): Int ={

      if(low>high)  {

        return low
      }

      val m1 = (low+high)/2

      val a_m1= a(m1)

      val b_m2= b(k-m1)

      if(a_m1 >= b_m2){

        medianOfMedianRec(low, m1-1)
        /*

 */
      }

      else{

        medianOfMedianRec(m1+1, high)
        /*

          println(medianOfMedian(num1,num2)-5.5<0.1)


            num1 = Array(1,3,8,12,15)
            num2 = Array(0,4,7,9,10,11)

            assert(medianOfMedian(num1,num2)-8<0.1)*/
      }
    }

    val a_left_idx =  medianOfMedianRec(0, a.length-1)

    val (a_left,b_left) = {
      if(a_left_idx<=0)
        (Integer.MIN_VALUE,b(k))
      else if (a_left_idx>a.length-1)
        (Integer.MIN_VALUE,b(Math.max(k - a.length,0)))
      else (a(a_left_idx), b(k - a_left_idx-1))
    }


    val (a_right,b_right) = {
      if(a_left_idx<=0)
        (Integer.MAX_VALUE,b(k))
      else if (a_left_idx>a.length-1)
        (Integer.MAX_VALUE, b(Math.max(k - a.length,0)))
      else (a(a_left_idx+1), b(k - a_left_idx))
    }

    val left_max = Math.max(a_left, b_left)

    val right_min = Math.min(a_right, b_right)


    if((totalLen & 1) == 1) left_max.toDouble

    else (left_max + right_min).toDouble/2
  }



  def main(args: Array[String]): Unit ={

    var num1 = Array(1,3,8,12)
    var num2 = Array(0,4,7,9)





   println(medianOfMedian(num1,num2))
/*


    num1 = Array(1,3,8,12,15)
    num2 = Array(0,4,7,9,10,11)

    println(medianOfMedian(num1,num2))



    // TODO this leads to indexOfBounds, k = 1, m1 = (0+1)/2 = 0, m2=1
    num1 = Array(1,3)
    num2 = Array(2)

    println(medianOfMedian(num1,num2))

*/


/*    num1 = Array(1,2)
    num2 = Array(3,4)

    assert(medianOfMedian(num1,num2)==2.5)

    num1 = Array()
    num2 = Array(3,4)

    assert(medianOfMedian(num1,num2)==3.5)
*/
  }
}
