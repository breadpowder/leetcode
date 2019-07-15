package question.trees.binaryTree.traversal

/*
144
Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
1
\
2
/
3

Output: [1,2,3]
Follow up: Recursive solution is trivial, could you do it iteratively?*/


//TODO no shit for null handling, use Option(null) = None

object PreOrderTravseralIterator {

  def preorderTraversal(root: TreeNode): List[Int] = {

      var result: List[Int] = List.empty

      var workToDo :List[Option[TreeNode]]  = List(Option(root))

      while(workToDo.nonEmpty) {

        workToDo.head match {

          case Some(work) => {
            result = work.value :: result
            workToDo = workToDo.tail
            workToDo = Option(work.left) :: Option(work.right) :: workToDo
          }
          case None => workToDo = workToDo.tail

        }
      }

      return result.reverse
  }



  def main(args:Array[String]) = {

     var root = new TreeNode(3)

    var levelOneL = new TreeNode(9)
    var levelOneR = new TreeNode(20)


    var levelTwoRL = new TreeNode(15)
    var levelTwoRR = new TreeNode(7)

    root.left =levelOneL
    root.right = levelOneR
    levelOneR.left = levelTwoRL
    levelOneR.right = levelTwoRR

    val result = preorderTraversal(root)

   println(result.mkString(","))
  }


}
