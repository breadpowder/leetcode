package question.recursion

import scala.annotation.tailrec


/*
2
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
 */


//FOUND: match null
class ListNode(var _x: Int = 0) {
      var next: ListNode = null
      var x: Int = _x
}


object AddTwoNumber {

  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {


    def addTwoNumberWithCarryOver(l: ListNode, r: ListNode, carryOver: Int): ListNode = {


      val (value:Int,carry:Int) = ((l.x + r.x + carryOver)%10, (l.x + r.x + carryOver)/10)
      val head = new ListNode(value)

      //TODO create a node to reuse the strucuture
      if (l.next == null && r.next!=null ) {
        head.next = addTwoNumberWithCarryOver(new ListNode(carry),r.next, 0)
      }

      else if (r.next == null && l.next !=null ) {
        head.next =  addTwoNumberWithCarryOver(l.next, new ListNode(carry),0)
      }

      else if(l.next == null && r.next==null )   {

        head.next = carry match {

          case 1 => new ListNode(1)

          case _ => null
        }
      }


      else {
        val next = addTwoNumberWithCarryOver(l.next, r.next, carry)
        head.next = next
      }
      head
    }

    addTwoNumberWithCarryOver(l1,l2,0)

  }


  @tailrec
  def printList(head: ListNode): Unit = {


    head.next match {

      case h: ListNode => {
        print(head.x + "->")
        printList(h)
      }

      case _ =>  print(head.x)
    }

  }

  def main(args: Array[String]): Unit = {


    val (l1_l, l1_r) = createTest1()

    printList(addTwoNumbers(l1_l, l1_r))

    println("")

    val (l2_l, l2_r) = createTest2()
    printList(addTwoNumbers(l2_l, l2_r))

  }

  def createTest1(): (ListNode, ListNode) ={

    val l1_1 = new ListNode (2)
    val l1_2 = new ListNode(4)
    val l1_3 = new ListNode(3)

    l1_1.next = l1_2
    l1_2.next = l1_3


    val l2_1 = new ListNode (5)
    val l2_2 = new ListNode(6)
    val l2_3 = new ListNode(4)

    l2_1.next = l2_2
    l2_2.next = l2_3

    (l1_1, l2_1)

  }

  def createTest2(): (ListNode, ListNode) ={

    val l1_1 = new ListNode (1)


    val l2_1 = new ListNode (9)
    val l2_2 = new ListNode(9)
    val l2_3 = new ListNode(9)

    l2_1.next = l2_2
    l2_2.next = l2_3

    (l1_1, l2_1)
  }



}
