package source.tree;

import java.util.Collections;
import java.util.List;
import source.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*
103. Binary Tree Zigzag Level Order Traversal
Medium

969

60

Favorite

Share
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
 */

//take away: reverse a list to use Collections.reverse(list);
public class BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {


        List<List<Integer>> result = new LinkedList<>();

        if(root==null) return result;

        Queue<TreeNode> q = new LinkedList<TreeNode>();

        q.add(root);

        int count =1;
        while(!q.isEmpty()){

            int size = q.size();
            List<Integer> level = new LinkedList<>();


            while(size!=0){

                TreeNode t = q.poll();
                level.add(t.val);
                if(t.left != null) q.add(t.left);
                if(t.right!=null) q.add(t.right);

                size--;
            }

            if((count &1) ==1)
                result.add(level);
            else
            { Collections.reverse(level);
                result.add(level);
            }
            count++;
        }

        return result;

    }
}

