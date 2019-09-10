package source.tree;


import source.common.TreeNode;

/*
124. Binary Tree Maximum Path Sum


Share
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from
some starting node to any node in the tree along the parent-child connections.
The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42

 */
public class BinaryTreeMaximumPathSum {

    public int maxPathSum(TreeNode root) {

        // Map<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();

        int[] max = new int[]{Integer.MIN_VALUE};


        dfs(root,max);



        return max[0];
    }


    public int dfs(TreeNode p, int[] arr) {//Map<TreeNode, Integer> map){

        if(p==null) return 0;

        int left = dfs(p.left, arr);

        int right = dfs(p.right, arr);

        int pathMax= Math.max(left > right ? left: right,0)+p.val;

        int max = (left > 0 ? left : 0) + (right>0?right:0) + p.val;

        if(max>arr[0]) arr[0] = max;

        return pathMax;

    }
}
