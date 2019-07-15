package question.trees.binaryTree.traversal

/*

105 Construct Binary Tree from Preorder and Inorder Traversal

For example, given

preorder = [3,9,4,6,20,15,7]
inorder = [4,9,6,3,15,20,7]
Return the following binary tree:

     3
   /   \
  9     20
 / \   /  \
4   6 15   7


 */


/*
class TreeNode(var _value: Int) {
     var value: Int = _value
     var left: TreeNode = null
     var right: TreeNode = null
}
*/


//TODO the orignial algorithm run stackoverflow

object BuildTreeFromPreOrderAndInorderTraversal {

  def buildTree(preorder: Array[Int], inorder: Array[Int]): TreeNode = {

    if(preorder.isEmpty) return null

    else if(preorder.length==1) return new TreeNode(preorder(0))

    else {
      val root = preorder(0)
      val rootNode = new TreeNode(root)

      val rootIdx = inorder.indexOf(root) + 1
      val (preorder_left, preorder_right) = preorder.splitAt(rootIdx)
      val (inorder_left, inorder_right) = inorder.splitAt(rootIdx)
      val leftTreeNode = buildTree(preorder_left.drop(1), inorder_left.dropRight(1))
      val rightTreeNode = buildTree(preorder_right, inorder_right)


      rootNode.left = leftTreeNode
      rootNode.right = rightTreeNode

      rootNode
    }
  }


  //TODO TRY TO optimize it because too many array copies, use index instead
  def buildTreeTail(preorder: Array[Int], inorder: Array[Int]): TreeNode = {


    if(preorder.isEmpty) return null

    abstract class Node

    case class LeftNode(preorder: Array[Int],inorder:Array[Int], parent: TreeNode) extends Node

    case class RightNode(preorder: Array[Int],inorder:Array[Int], parent: TreeNode) extends Node


    def splitNode(preorder: Array[Int], inorder: Array[Int]) = {

      val rootIdx = inorder.indexOf(preorder(0)) + 1
      val (preorder_left, preorder_right) = preorder.splitAt(rootIdx)
      val (inorder_left, inorder_right) = inorder.splitAt(rootIdx)

      (preorder_left.drop(1), preorder_right,inorder_left.dropRight(1),  inorder_right)
    }

    @scala.annotation.tailrec
    def build(workToDo:  List[Node]): Unit = {

      var newWork = workToDo.tail

      workToDo.head match {


       case LeftNode(preorder, inorder, parent) if preorder nonEmpty => {

         val (preorder_left, preorder_right,inorder_left, inorder_right) = splitNode(preorder,inorder)
         val child = new TreeNode(preorder(0))
         parent.left =  child
         newWork = LeftNode(preorder_left, inorder_left,child)::RightNode(preorder_right, inorder_right,child)::newWork

       }
         //TODO for empty case, just recurse on the rest task, newWork

      // case RightNode(preorder, inorder, parent) if preorder isEmpty  => ()
       case RightNode(preorder, inorder, parent) if preorder nonEmpty => {

         val (preorder_left, preorder_right,inorder_left, inorder_right) = splitNode(preorder,inorder)
         val child = new TreeNode(preorder(0))
         parent.right =  child
         newWork = LeftNode(preorder_left, inorder_left,child)::RightNode(preorder_right, inorder_right,child)::newWork
       }

       case _ => {}



       }

      //TODO this is outside match clause, why
      //TODO condition to stop work, gauge
      if(newWork.nonEmpty) {
        build(newWork)
      }


    }

    val (preorder_left, preorder_right,inorder_left, inorder_right) = splitNode(preorder,inorder)
    val root = new TreeNode(preorder(0))

    build(List(LeftNode(preorder_left, inorder_left,root),RightNode(preorder_right, inorder_right,root)))
    root

  }


  //TODO TRY TO optimize it because too many array copies, use index instead
  def buildTreeTailAlgorithm(preorder: Array[Int], inorder: Array[Int]): TreeNode = {

    if(preorder.isEmpty) return null

    abstract class Node

    case class LeftNode(startIdx: Int ,endIdx:Int, parent: TreeNode) extends Node

    case class RightNode(startIdx: Int,endIdx:Int, parent: TreeNode) extends Node



    def splitNode(start: Int, end:Int): Int = {

      val rootIdx = inorder.indexOf(preorder(start))


      rootIdx
    }

    @scala.annotation.tailrec
    def build(workToDo:  List[Node]): Unit = {

      var newWork = workToDo.tail

      workToDo.head match {

        case LeftNode(startIdx, endIdx, parent) if endIdx >= startIdx => {

          val splitIdx = splitNode(startIdx, endIdx)
          val child = new TreeNode(preorder(startIdx))
          parent.left =  child
          if(endIdx>startIdx) {
            newWork = LeftNode(startIdx + 1, splitIdx, child) :: RightNode(splitIdx + 1, endIdx, child) :: newWork
          }
        }
        //TODO for empty case, just recurse on the rest task, newWork

        // case RightNode(preorder, inorder, parent) if preorder isEmpty  => ()
        case RightNode(startIdx, endIdx, parent) if endIdx >= startIdx=> {

          val splitIdx = splitNode(startIdx, endIdx)
          val child = new TreeNode(preorder(startIdx))
          parent.right =  child
          if(endIdx>startIdx) {
            newWork = LeftNode(startIdx + 1, splitIdx, child) :: RightNode(splitIdx + 1, endIdx, child) :: newWork
          }
        }

        case _ => {}



      }

      //TODO this is outside match clause, why
      //TODO condition to stop work means all tasks are done
      if(newWork.nonEmpty) {
        build(newWork)
      }


    }

    val splitIdx = splitNode(0, preorder.length-1)
    val root = new TreeNode(preorder(0))

    build(List(LeftNode(1, splitIdx,root),RightNode(splitIdx+1, preorder.length -1 ,root)))
    root

  }



  def preorder_travseral(node: TreeNode):Unit ={


    if(node==null) {
      ()
    }

    else {
      println(node._value)
      preorder_travseral(node.left)
      preorder_travseral(node.right)
    }
  }


  def inorder_travseral(node: TreeNode):Unit ={


    if(node==null) {
      ()
    }

    else {
      inorder_travseral(node.left)
      println(node._value)
      inorder_travseral(node.right)
    }
  }

  def main(args: Array[String]): Unit = {


   /* var root = new TreeNode(3)

    var levelOneL = new TreeNode(9)
    var levelOneR = new TreeNode(20)


    var levelTwoRL = new TreeNode(15)
    var levelTwoRR = new TreeNode(7)

    root.left =levelOneL
    root.right = levelOneR
    levelOneR.left = levelTwoRL
    levelOneR.right = levelTwoRR*/


    //var buildRoot =buildTreeTailAlgorithm(Array(3,9,20,15,7),Array(9,3,15,20,7))
    //val buildRoot =buildTree(Array(3,9),Array(9,3))

    var buildRoot =buildTreeTailAlgorithm(Array(1,2,3),Array(3,2,1))


    preorder_travseral(buildRoot)
    inorder_travseral(buildRoot)



  }
}
