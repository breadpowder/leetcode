package question

object HouseRobberIII {

  /* 337

  The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root."
  Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree".
   It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:

Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \
     3   1

Output: 7
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:

Input: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \
 1   3   1

Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.

     3
    / \
   6   5
  / \   \
 1   3   100


DP(X)= DP(X->LEFT) + DP(X->RIGHT) OR DP(X->LEFT->LEFT) + DP(X->LEFT->RIGHT) +
   */


  //   Definition for a binary tree node.
     class TreeNode(var _value: Int) {
      var value: Int = _value
      var left: TreeNode = null
      var right: TreeNode = null
     }

    def rob(root: TreeNode): Int = {


        if (root==null || root.value == null) return 0

        val notSelected = rob(root.left) + rob(root.right)


        var left,right = 0
        if(root.left!=null)
           left = rob(root.left.left) + rob(root.left.right)

        if(root.right!=null)
          right = rob(root.right.left) + rob(root.right.right)

        val selected = root.value + left + right


        Math.max(notSelected, selected)

    }


    def main(args: Array[String]): Unit = {


      val root = new TreeNode(3)
      val left_l1 = new TreeNode(6)
      val right_l1 = new TreeNode(5)
      val left_left_l2 = new TreeNode(1)
      val left_right_l2 = new TreeNode(3)
      val right_right_l2 = new TreeNode(100)

      root.left = left_l1
      root.right = right_l1


      left_l1.left = left_left_l2
      left_l1.right = left_right_l2

      right_l1.right = right_right_l2


      println(rob(root))
    }
}
