/*

307 Range sum query
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.

Example:

Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:

The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.

*/

//Take away

//1. recusrive data structure definiation
 // CANNOT use NODE.left or NODE.class, only fit pattern matching, do t
// case class Node(low: Int, high:Int, v:Int, left:Node=null, right:Node=null)
//
// trait Node[+A]
// case class Branch(left: Node[Int], right: Node[Int]) extends Node[Int]
// case object Left extends Node[Nothing]


// 2. F-Bound

// trait Node[A <: Node[A]]{

//   def child: Node[A]
//}


//3. Segment Tree
//a.  all values are on leaf level, see buildTree left and right method
//b. upodate method, use   this   to decide go left or giht   val mid = (node.low + node.high)/2
//c sumRange method      use to decide go left or right   val mid = (node.low + node.high)/2



object RangeSumQuery {

class NumArray(_nums: Array[Int]) {

    val nums = _nums


    case class Node(low: Int, high:Int, v:Int, left:Node=null, right:Node=null)

    var root : Node = if (nums==null || nums.isEmpty) null else buildTree(0, nums.length-1)


    def buildTree(l: Int, h: Int): Node ={

      if(l==h) return new Node(l, h, nums(l))

      val mid = (l+h)/2

      val left = buildTree(l, mid)
      val right = buildTree(mid+1, h)

      val root = new Node(l, h, left.v + right.v, left, right)
      return root
    }



    def update(i: Int, v: Int): Unit  = {



      def updateRec(l: Int, h: Int, node: Node): Node= {

        if((l == h) && (l== i))  return new Node(l,h,v)

        val mid = (node.low + node.high)/2

        if(i<=mid) {
          val left = updateRec(l,mid, node.left)
          return  new Node(l,h,left.v + node.right.v , left, node.right)
        }

        else   {
          val right= updateRec(mid+1, h, node.right)
          return  new Node(l,h,node.left.v + right.v ,node.left, right)

        }

      }

      if(root!=null){

        root = updateRec(0, nums.length-1, root)
      }
    }


    def sumRange(i: Int, j: Int): Int = {
      if(root==null) return 0

      def sumRangeRec(l:Int, h: Int, node:Node): Int = {

        if(node==null) return 0

        if(node.low == l && node.high == h) return node.v

        val mid = (node.low + node.high)/2

        if(h <= mid) return sumRangeRec(l, h, node.left)

        else if(l>mid) return sumRangeRec(l, h, node.right)

        else return sumRangeRec(l, mid, node.left) +  sumRangeRec(mid+1, h, node.right)
      }

      sumRangeRec(i, j, root)
    }

  }

  def main(args: Array[String]): Unit = {

/*
    val numArr = new NumArray(Array(1,3,5))

    println(numArr.sumRange(0,2))


    numArr.update(1,2)

    val node = numArr.root
    println(numArr.sumRange(0,2))
*/

/*    val numArr = new NumArray(Array(9,-8))
    numArr.update(0,3)

    val node = numArr.root

    println(numArr.sumRange(1,1))*/


    val numArr = new NumArray(Array(0,9,5,7,3))

    val node = numArr.root
    println(numArr.sumRange(2,4))


  }

}

/**
  * Your NumArray object will be instantiated and called as such:
  * var obj = new NumArray(nums)
  * obj.update(i,`val`)
  * var param_2 = obj.sumRange(i,j)
  */