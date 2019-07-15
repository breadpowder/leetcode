package question

object QuickSort {


  def quickSortQuick(arr: List[Int]) : List[Int] = {

    if(arr.size/2==0) arr


      else {
        val pivot = arr(0)
      

        quickSortQuick(arr.filter(_ < pivot)) ++ arr.filter( _ == pivot) ++ quickSortQuick(arr.filter(_ > pivot))
        }

  }


  def quickSort(arr: Array[Int]) : Array[Int] = {


    def quickSort(arr: Array[Int], start: Int, end: Int): Array[Int] = {


      def inplaceOrder(): Int = {

        val pivot = arr(start)

        var (scanIdx: Int, pivotIdx: Int) = (start + 1, start)

        while (scanIdx <= end) {

          if (arr(scanIdx) < pivot) {

            // begain status: pivot at pivot index, set pivotIdx to arr(scanIdx), set scan idx to arr(pivot+1)
            // end status: pivot at pivot index
            val tmp =  arr(pivotIdx)
            arr(pivotIdx) = arr(scanIdx)
            pivotIdx += 1
            arr(scanIdx) = arr(pivotIdx)
            arr(pivotIdx) = tmp

          }

          scanIdx += 1
        }

        pivotIdx

      }

      if (end - start <= 0) arr


      else {

        val idx = inplaceOrder()

        quickSort(arr, start, idx - 1)
        quickSort(arr, idx + 1, end)

      }
    }

    quickSort(arr, 0, arr.length - 1)

  }



  implicit  def swap(arr:Array[Int], i:Int, j:Int) = {

    var tmp = arr(i)
    arr(i) = arr(j)
    arr(j) = tmp
  }

  def quickSortConcise(arr: Array[Int], start: Int, end: Int)(implicit swap: (Array[Int],Int, Int) => Unit): Array[Int] = {


    def partition(low: Int, high: Int) :Int = {

      var pivot = arr(high)
      var next = low

      for {
        idx <- low to high
        if (arr(idx) < pivot)
      } {
        swap(arr,idx, next)
        next+=1
      }


      swap(arr,next, high)
      next

    }

    if(start>=end) {return arr}

    val pivot = partition(start,end)
    quickSortConcise(arr,start, pivot-1)
    quickSortConcise(arr,pivot+1, end)

  }






  def main(args: Array[String]):Unit={

    var arr = Array(7,11,3,8,4,6)


    println(quickSort(arr).mkString("."))
    assert(List(3,4,6,7,8,11).sameElements(quickSort(arr)))


    arr = Array(7,11,3,8,4,6)
    assert(List(3,4,6,7,8,11).sameElements(quickSortConcise(arr,0, arr.length-1)))

  }

}




