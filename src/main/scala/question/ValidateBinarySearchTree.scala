package question


/*

98
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:

Input:
2
/ \
1   3
Output: true
Example 2:

5
/ \
1   7
/ \
3   6
Output: false
Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
is 5 but its right child's value is 4.*/

//Review fail quick

//
/*

class Solution(object):
  def isValidBST(self, root):
      self.last = -float('inf')
      self.flag = True
      self.inorder(root)
      return self.flag

  def inorder(self, root):
      if not root: return
      self.inorder(root.left)
      if self.last >= root.val:
          self.flag = False
      self.last = root.val
      self.inorder(root.right)
 */

 class TreeNode(var _value: Int) {
     var value: Int = _value
     var left: TreeNode = null
     var right: TreeNode = null
   }

object ValidateBinarySearchTree {



  def isValidBST(root: TreeNode): Boolean = {

    var lastVisitNumber:Option[Int] = None

    def visit(local_root: TreeNode): Boolean ={

      if(local_root == null) {
         return true
      }

      val isLeft = visit(local_root.left)

      val current = lastVisitNumber match {

        case Some(i) if (local_root.value <= i)  => false

        case _ => true
      }
      lastVisitNumber = Some(local_root.value)

      val right = visit(local_root.right)

      return isLeft && current && right

    }
      visit(root)

  }


  def test1(): TreeNode =
  {
    val root = new TreeNode(2)
    val l = new TreeNode(1)
    val r = new TreeNode(3)

    root.left = l
    root.right = r
    root
  }

  def test2(): TreeNode =
  {
    val root = new TreeNode(5)
    val l1 = new TreeNode(1)
    val r1 = new TreeNode(7)
    val l1_l = new TreeNode(3)
    val l1_r = new TreeNode(6)

    root.left = l1
    root.right = r1
    l1.left = l1_l
    l1.right = l1_r

    root
  }

  def main(args: Array[String]): Unit = {


    assert(isValidBST(test1))
   assert(!isValidBST(test2))

  }


}
