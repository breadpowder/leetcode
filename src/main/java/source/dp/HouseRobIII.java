package source.dp;

import source.common.TreeNode;

import java.util.HashMap;
import java.util.Map;


//Take away
//1.  topological root node depends on child, dependancy with memorization
//2.  substructure compute one state, i.e rob or not and return  vs compute two states together see optimize version
//3. lambda function needs to get finalized value


/*

337. House Robber III
Medium

1536

31

Favorite

Share
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

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
 */

public class HouseRobIII {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public int rob(TreeNode root) {


            Map<TreeNode, Map<Boolean, Integer>> dp = new HashMap<TreeNode,Map<Boolean,Integer>>();

            return Math.max(robRec(root, true, dp),robRec(root, false, dp));


        }



        public int robRec(TreeNode root, Boolean rob, Map<TreeNode, Map<Boolean, Integer>> result) {

            if (root == null) return 0;

            if (result.containsKey(root) && result.get(root).containsKey(rob)) return result.get(root).get(rob);

            int rob_left = robRec(root.left, true, result);
            int n_rob_left = robRec(root.left, false, result);


            int rob_right = robRec(root.right, true, result);
            int n_rob_right = robRec(root.right, false, result);


            int value = 0;
            if (rob) {
                value = n_rob_left + n_rob_right + root.val;

            } else {
                value = Math.max(Math.max(rob_left + rob_right, n_rob_left + rob_right), Math.max(rob_left + n_rob_right, n_rob_left + n_rob_right));
            }


            result.computeIfAbsent(root, k -> new HashMap<Boolean, Integer>());

            //TODO final in the lambda expression
            final Integer va = value;

            result.computeIfPresent(root, (k, v) -> {
                v.put(rob, va);
                return v;
            });

            return value;
        }


        public int[] roboptimize(TreeNode root){

            int[] robs = new int[2];

            if(root==null) return robs;


            int[] rob_left = roboptimize(root.left);

            int[] rob_right =roboptimize(root.right);

            //not _ rob
            robs[0] =  Math.max(rob_left[0], rob_left[1]) +  Math.max(rob_right[0], rob_right[1]);
            robs[1] =  rob_left[0] +  rob_right[0] + root.val;


            return robs;



        }

    }
}
