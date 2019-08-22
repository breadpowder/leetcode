package source.tree.Trie;

import source.common.TreeNode;

/*

654
 Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

    The root is the maximum number in the array.
    The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
    The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.

Construct the maximum tree by the given array and output the root node of this tree.

Example 1:

Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    /
     2  0
       \
        1

Note:

    The size of the given array will be in the range [1,1000].

 */

public class MaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {

        return constructTree(0, nums.length-1, nums);

    }


    TreeNode constructTree(int l, int r, int[] nums){

        if(l>r) return null;

        int idx = findMax(l, r, nums);
        TreeNode root = new TreeNode(nums[idx]);

        root.left = constructTree(l, idx-1, nums);

        root.right = constructTree(idx+1, r, nums);

        return root;
    }


    int findMax(int l, int r, int[] nums){

        int idx = l;
        int max = Integer.MIN_VALUE;

        for(int i=l; i<=r; i++){
            if(nums[i] > max){

                idx =i;
                max = nums[i];

            }
        }

        return idx;

    }
}
