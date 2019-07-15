package source.tree;
/*863. All Nodes Distance K in Binary Tree
        Medium


        Share
        We are given a binary tree (with root node root), a nodeId node, and an integer value K.

        Return a list of the values of all nodes that have a distance K from the nodeId node.  The answer can be returned in any order.



        Example 1:

        Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodeId = 5, K = 2

        Output: [7,4,1]

        Explanation:
        The nodes that are a distance 2 from the nodeId node (with value 5)
        have values 7, 4, and 1.



        Note that the inputs "root" and "nodeId" are actually TreeNodes.
        The descriptions of the inputs above are just serializations of these objects.*/

import java.util.*;
import java.util.stream.Collectors;


//take away
//create object in stack, targetNode = new vs targetNode =n
//https://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value
public class AllNodesDistanceKInBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public class Node{

        List<Node> neighboors = new LinkedList<Node>();

        public void addNode(Node n){
            if(n!=null) {
                neighboors.add(n);
            }
        }
        
        int value;


        public Node(int value) {
            this.value = value;
        }
    }


    //THIS method is wrong to due reference
    public Node buildToGrah(TreeNode root, int target){

        if(root==null) return null;

        Node n = new Node(root.val);



        Node left = buildToGrah(root.left, target);

        Node right = buildToGrah(root.right, target);


        if(left!=null) {
            n.addNode(left);
            left.addNode(n);
        }

        if(right!=null) {
            n.addNode(right);
            right.addNode(n);
        }

        //TODO nodeId node is in the stack
    /*    if(root.val== nodeId){
            targetNode = n;
        }*/


        return n;
    }




    public Node findNode(Node root, int target,Set<Node> visited){

        if(root==null) return null;

        visited.add(root);


        if(root.value == target) return root;


        Node node = null;


        for(Node next: root.neighboors) {

            //TODO the order and prevents double vistis
            //TODO the order and prevents double vistis

            if(!visited.contains(next)) {
                node = findNode(next, target, visited);
                if (node != null) {
                    break;
                }
            }

        }
        return node;
    }




    public List<Integer> searchNode(Node searchRoot, int K) {

         Queue<Node> q = new LinkedList<Node>();
         q.add(searchRoot);

         int steps = 0;

         Set<Node> visited = new HashSet<Node>();

         List<Integer> result = new LinkedList<>();

        if(K==0) {

            result.add(searchRoot.value);
        }


        while (!q.isEmpty() && steps != K) {

             Queue<Node> tmpQ = new LinkedList<Node>();


             while (!q.isEmpty()) {

                 Node next = q.poll();
                 visited.add(next);

                 for (Node node : next.neighboors) {
                     if (!visited.contains(node)) {
                         //visited.add(next);
                         tmpQ.add(node);
                     }

                 }
             }

             if ((++steps) == K) {
                 result.addAll(tmpQ.stream().map(n -> n.value).collect(Collectors.toList()));
             } else {

                 q.addAll(tmpQ);
             }

         }
         return result;
     }




    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {


        List<Integer> result = new LinkedList<Integer>();


        Node graphRoot = buildToGrah(root, target.val);

        Node targetNode = findNode(graphRoot, target.val, new HashSet<Node>());

        return searchNode(targetNode,K)   ;
    }

}