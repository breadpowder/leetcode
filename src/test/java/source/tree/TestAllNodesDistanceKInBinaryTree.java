package source.tree;

import org.junit.Before;
import org.junit.Test;
import source.tree.AllNodesDistanceKInBinaryTreeOptimized.TreeNode;


import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestAllNodesDistanceKInBinaryTree {


    public AllNodesDistanceKInBinaryTreeOptimized testee = new AllNodesDistanceKInBinaryTreeOptimized();



    @Test
    public void testKDistanceTree(){

        TreeNode r = new TreeNode(1);

        r.left = new TreeNode(2);
        r.right = new TreeNode(3);


        List<Integer> result = testee.distanceK(r, r.left,2);
        assertTrue(result.size()==1);
        assertTrue(result.get(0)==3);
    }


    @Test
    public void testKDistanceTree2(){

        TreeNode r = new TreeNode(0);

        r.left = new TreeNode(1);
        r.left.left = new TreeNode(3);

        r.left.right = new TreeNode(2);



        List<Integer> result = testee.distanceK(r, r.left.right,1);
        assertTrue(result.size()==1);
        assertTrue(result.get(0)==1);
    }


}
