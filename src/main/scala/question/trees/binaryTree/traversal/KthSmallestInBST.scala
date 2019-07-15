package question.trees.binaryTree.traversal

/*
230. Kth Smallest Element in a BST
Medium


Share
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1

Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
Follow up:
What if the BST is modified (insert/delete operations) often and
you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?


 */
object KthSmallestInBST {


  class TreeNode(var _value: Int) {
    var value: Int = _value
    var left: TreeNode = null
    var right: TreeNode = null
  }

  def kthSmallest(root: TreeNode, k: Int): Int = {

    var count = 0

    var result =0

    def travseral (root: TreeNode): Unit= {

      if(root==null) return

      else if(count == k) return

      else {
        travseral(root.left)

        count += 1

        if(count==k) {
          result = root.value

        }
        travseral(root.right)
      }

    }

    travseral(root)
    result
  }


  def main(args: Array[String]): Unit = {

    var root = new TreeNode(9)

  var levelOneL = new TreeNode(7)
  var levelOneR = new TreeNode(15)


  var levelTwoRL = new TreeNode(11)
  var levelTwoRR = new TreeNode(20)

  root.left =levelOneL
  root.right = levelOneR
  levelOneR.left = levelTwoRL
  levelOneR.right = levelTwoRR

    println(kthSmallest(root,3))
  }
}
