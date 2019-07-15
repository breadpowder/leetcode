package source.tree;

/*

113 Pathsumiii
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]

 */

import source.common.TreeNode;

import java.util.LinkedList;
import java.util.List;

//take away: 1. review clean code, handle root node in each stack
//2. check null before proceed to next level, otherwise, each node will be added twice
// to the result list, due to both left and right are empty
public class PathSumIII {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {



        List<List<Integer>> result =  new LinkedList<List<Integer>>();


        rec(new LinkedList<Integer>(), result, sum, root);

        return result;
    }

    public void rec(LinkedList<Integer> current, List<List<Integer>> result, int remain, TreeNode root){

        //this condition is worng remain<0 |
        if(root==null && remain==0 && !current.isEmpty()) {
            result.add(new LinkedList<Integer>(current));
            return;
        }


        if(root == null) return;

/*        StringBuilder sb = new StringBuilder();

        for(Integer cur: current){

            //System.out.println("");
            sb.append(cur.toString()+ " ");
        }

        System.out.println(sb.toString());*/


        /*if(root.left == null && root.right ==null && root.val==remain) {
            current.add(root.val);

            current.removeLast();
            return;
        }*/



        int val = root.val;
        current.add(root.val);

        if(root.left!=null){
            rec(current, result,remain - val, root.left);

        }

        if(root.right!=null){
            rec(current, result,remain - val, root.right);
        }


        if(root.left==null && root.right==null){
            rec(current, result,remain - val, null);

        }

        current.removeLast();




    }
}
