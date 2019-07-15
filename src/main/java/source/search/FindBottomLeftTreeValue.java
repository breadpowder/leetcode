package source.search;

/*
513. Find Bottom Left Tree Value
Medium

569

94

Favorite

Share
Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
Input:

    2
   / \
  1   3

Output:
1
Example 2:
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7
 */

//take away:

//edge case,         findRecursive(result, max_depth, 1, root);,  cur_depth inital as 1, so to trick the comparision since max_depth=0;
public class FindBottomLeftTreeValue {


  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;

      TreeNode(int x) {
          val = x;
      }
  }



    public int findBottomLeftValue(TreeNode root) {



        int[] result = new int[1];

        int[] max_depth = new int[1];


        findRecursive(result, max_depth, 1, root);

        return result[0];

    }


    public void findRecursive(int[] result,  int[] max_depth, int cur_depth, TreeNode root){

        if(root == null) return;

        if(root.left!=null)
        {
            findRecursive(result,  max_depth, cur_depth+1, root.left);
        }

        //leaf node
        if(root.left == null && root.right == null){

            if(cur_depth > max_depth[0]){
                result[0]    =  root.val;
                max_depth[0] = cur_depth;
            }
        }

        if(root.right!=null)
        {
            findRecursive(result,  max_depth, cur_depth+1, root.right);
        }



    }

}
