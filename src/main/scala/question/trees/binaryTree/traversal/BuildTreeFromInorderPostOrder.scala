package question.trees.binaryTree.traversal

/*

106
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

  3
/  \
9   20
   /  \
 15   7

 */


class TreeNode(var _value: Int) {
  var value: Int = _value
  var left: TreeNode = null
  var right: TreeNode = null
}


//TODO find the root and sub-structure
//TODO what's the next
object BuildTreeFromInorderPostOrder {



  def buildTree(inorder: Array[Int], postorder: Array[Int]): TreeNode = {


    def buildTreeRecursive(inOrder: Array[Int], postOrder: Array[Int]): TreeNode = {



      if(inOrder.isEmpty) return null


        else {
        val root = postOrder.last

        val split = inOrder.indexOf(root)


        val rootNode = new TreeNode(root)


        val (inOrder_left, inOrder_right) = inOrder.splitAt(split)
        val (postOrder_left, postOrder_right) = postOrder.splitAt(split)


        val leftRoot = buildTreeRecursive(inOrder_left, postOrder_left)
        rootNode.left = leftRoot
        val rightRoot = buildTreeRecursive(inOrder_right.drop(1), postOrder_right.dropRight(1))
        rootNode.right = rightRoot

        rootNode
      }

    }


    def buildTreeRecursiveIdx(inOrder_left: Int, inOrder_right: Int, postOrder_left: Int, postOrder_right:Int): TreeNode = {



      if(inOrder_right-inOrder_left<0) return null

      else if(inOrder_right == inOrder_left ) return new TreeNode(inorder(inOrder_left))

      else {
        val root = postorder(postOrder_right)

        val split = inorder.indexOf(root) - inOrder_left

        val rootNode = new TreeNode(root)



        val leftRoot = buildTreeRecursiveIdx(inOrder_left, inOrder_left + split-1, postOrder_left, postOrder_left+ split-1)
        rootNode.left = leftRoot
        val rightRoot = buildTreeRecursiveIdx(inOrder_left + split + 1, inOrder_right, postOrder_left + split, postOrder_right-1)
        rootNode.right = rightRoot

        rootNode
      }

    }

    abstract class Node

    case class LeftNode(inOrder_left: Int ,inOrder_right:Int, postOrder_left: Int, postOrder_right: Int, parent: TreeNode) extends Node

    case class RightNode(inOrder_left: Int ,inOrder_right:Int, postOrder_left: Int, postOrder_right: Int, parent: TreeNode) extends Node



    def split(inOrder_left: Int ,inOrder_right:Int, postOrder_left: Int, postOrder_right: Int, newWork: List[Node] = List.empty): (TreeNode, List[Node]) = {


      val root = postorder(postOrder_right)
      val rootNode = new TreeNode(root)

      val split = inorder.indexOf(root) - inOrder_left

      (rootNode,LeftNode(inOrder_left, inOrder_left + split-1, postOrder_left, postOrder_left+ split-1,rootNode) :: RightNode(inOrder_left + split + 1, inOrder_right, postOrder_left + split, postOrder_right-1, rootNode) :: newWork)


    }

    @scala.annotation.tailrec
    def buildTreeTailRecursive( work : List[Node]): Unit = {



      val first = work.head

      var workToDo :List[Node] = work.tail


      first match {


        case LeftNode(inOrder_left: Int ,inOrder_right:Int, postOrder_left: Int, postOrder_right: Int, parent: TreeNode) if inOrder_left == inOrder_right => {


          parent.left = new TreeNode(inorder(inOrder_left))
        }

        case LeftNode(inOrder_left: Int ,inOrder_right:Int, postOrder_left: Int, postOrder_right: Int, parent: TreeNode) if inOrder_right > inOrder_left
          => {

          var tuple = split(inOrder_left, inOrder_right, postOrder_left, postOrder_right, workToDo)
          parent.left = tuple._1

          workToDo = tuple._2


        }

        case RightNode(inOrder_left: Int ,inOrder_right:Int, postOrder_left: Int, postOrder_right: Int, parent: TreeNode) if inOrder_left == inOrder_right => {

          parent.right = new TreeNode(inorder(inOrder_left))

        }


        case RightNode(inOrder_left: Int ,inOrder_right:Int, postOrder_left: Int, postOrder_right: Int, parent: TreeNode) if inOrder_right> inOrder_left
        => {

          var tuple= split(inOrder_left, inOrder_right, postOrder_left, postOrder_right, workToDo)
          parent.right= tuple._1

          workToDo = tuple._2

        }

        case _ =>

      }

       if(workToDo.nonEmpty){

         buildTreeTailRecursive(workToDo)
       }

    }



     // buildTreeRecursive(inorder,postorder)
    // buildTreeRecursiveIdx(0, inorder.length-1, 0, postorder.length-1)

    if(inorder isEmpty) return null
    val (rootNode, newWork) = split(0, inorder.length-1, 0, postorder.length-1)

    buildTreeTailRecursive(newWork)

    rootNode
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



  def postorder_travseral(node: TreeNode):Unit ={


    if(node==null) {
      ()
    }

    else {

      postorder_travseral(node.left)
      postorder_travseral(node.right)
      println(node._value)
    }
  }




  def main(args: Array[String]): Unit = {


    var buildRoot =buildTree(Array(9,3,15,20,7),Array(9,15,7,20,3))

    //var buildRoot =buildTree(Array(1,2,3,4),Array(3,2,4,1))

    //1
    //  \
    //    4
    //  /
    // 2
    //  \
    //   3


    inorder_travseral(buildRoot)
    postorder_travseral(buildRoot)




  }
}
