package question.trees.binaryTree.traversal

/*

94
Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?

*/

//TODO workToDO put work.right first

object InorderTraversalIterator {


  abstract class Work

  case class WorkToDo(node: Option[TreeNode])  extends  Work

  case class WorkDone(node:Option[TreeNode])  extends  Work

  def inorderTraversal(root: TreeNode): List[Int] = {


    var result: List[Int] = List.empty

    var works :List[Work]  = List(WorkToDo(Option(root)))


    while(works.nonEmpty) {

      works.head match {

        case WorkToDo(Some(work)) => {

          works = works.tail
          works = WorkToDo(Option(work.left)) :: WorkDone(Option(work)):: WorkToDo(Option(work.right)) ::works
        }
        case WorkDone(Some(work)) => {
          result = work.value :: result
          works = works.tail
        }

        case _ =>   works = works.tail

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

    val result = inorderTraversal(root)

    println(result.mkString(","))
  }
}
