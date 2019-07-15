package source;

/*
111. Minimum Depth of Binary Tree
Easy

700

375

Favorite

Share
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its minimum depth = 2.
 */


public class MinimumDepthofBinaryTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int minDepth(TreeNode root) {

        if(root==null) return 0;

        int left = minDepth(root.left);

        int right = minDepth(root.right);

        return (left < right) ? (left + 1) : (right +1);

    }



}
