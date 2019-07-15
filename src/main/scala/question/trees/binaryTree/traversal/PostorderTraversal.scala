package question.trees.binaryTree.traversal

/*

Given a binary tree, return the postorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]
 */


//TODO workToDO put work.right first

object PostorderTraversal {



  def postorderTraversal(root: TreeNode): List[Int] = {

    var result: List[Int] = List.empty

    var workToDo :List[Option[TreeNode]]  = List(Option(root))

    while(workToDo.nonEmpty) {

      workToDo.head match {

        case Some(work) => {
          result = work.value :: result
          workToDo = workToDo.tail
          workToDo = Option(work.right) ::  Option(work.left) ::workToDo
        }
        case None => workToDo = workToDo.tail

      }
    }

    return result

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

    val result = postorderTraversal(root)

    println(result.mkString(","))
  }
}
