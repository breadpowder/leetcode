package question

object SwapNodes {


  class ListNode(var _x: Int = 0) {
       var next: ListNode = null
       var x: Int = _x
     }

  def swapPairs(head: ListNode): ListNode = {


    if(head ==null || head.next==null)
      return head


    else {
      var iter = head


      val next = iter.next
      val next_next = iter.next.next


      val header = swapPairs(next_next)

      iter.next = header
      next.next = iter

      return next
    }

  }


  def main(args: Array[String]): Unit = {


    val first= new ListNode(1)
    val sec = new ListNode(2)
    val third = new ListNode(3)
    val fourth = new ListNode(4)


    first.next =sec
    sec.next = third
    third.next = fourth

    val sfirst = swapPairs(first)
    assert(sfirst.x==2)
    assert(sfirst.next.x==1)
    assert(sfirst.next.next.x==4)
    assert(sfirst.next.next.next.x==3)
  }
}
