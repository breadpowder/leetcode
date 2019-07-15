package source.tree;

import java.util.List;
import java.util.LinkedList;


//take away
// leetcode 114
// can use linkedList for preorder traversal,
public class FlattenBinaryTreeToLinkedList {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public void flatten(TreeNode root) {

        // rec(root);

        if (root == null) return;

        //TODO can use stack as well, a bit slower
        LinkedList<TreeNode> q = new LinkedList<TreeNode>();

        q.add(root);


        while (!q.isEmpty()) {

            TreeNode p = q.get(0);

            q.remove(0);

            if (p.right != null) {
                q.add(0, p.right);

            }

            if (p.left != null) {
                q.add(0, p.left);
            }

            p.left = null;

            //TODO should add first and then call is empty to ensure not empty then

            if (!q.isEmpty()) {

                p.right = q.get(0);
            }

        }
    }

    public static void main(String[] args){
    //[1,2,5,3,4,null,6]

        TreeNode r =  new TreeNode(1);

        TreeNode r_l = new TreeNode(2);

        TreeNode r_r = new TreeNode(3);


        r.left = r_l;

        r.right = r_r;

        FlattenBinaryTreeToLinkedList flatten = new FlattenBinaryTreeToLinkedList();

        flatten.flatten(r);

        TreeNode n = r;
        while(n!=null){

            System.out.println(n.val);

            n = n.right;
        }
    }

}
