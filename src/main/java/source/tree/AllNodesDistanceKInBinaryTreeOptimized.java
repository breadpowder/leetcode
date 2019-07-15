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

public class AllNodesDistanceKInBinaryTreeOptimized {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    //THIS method is wrong to due reference
    public TreeNode buildToGrah(TreeNode root,  Map<TreeNode, List<TreeNode>> nodeToNeighbour){

        if(root==null) return null;

        nodeToNeighbour.putIfAbsent(root, new LinkedList<TreeNode>());


        TreeNode left = buildToGrah(root.left, nodeToNeighbour);

        TreeNode right = buildToGrah(root.right, nodeToNeighbour);


        if(left!=null) {

            nodeToNeighbour.computeIfPresent(root, (k,v) -> {
                v.add(left);
                return v;
            });

            nodeToNeighbour.computeIfPresent(left,(k,v) ->{
                v.add(root);
                return v;
            });

        }

        if(right!=null) {

            nodeToNeighbour.computeIfPresent(root, (k,v) -> {
                v.add(right);
                return v;
            });

            nodeToNeighbour.computeIfPresent(right,(k,v) ->{
                v.add(root);
                return v;
            });

           /* n.addNode(right);
            right.addNode(n);*/
        }

        //TODO nodeId node is in the stack
    /*    if(root.val== nodeId){
            targetNode = n;
        }*/


        return root;
    }




    public List<Integer> searchNode(TreeNode searchRoot, int K, Map<TreeNode, List<TreeNode>> nodes) {
        List<Integer> result = new LinkedList<>();

         if(!nodes.containsKey(searchRoot)) return  result;

         Queue<TreeNode> q = new LinkedList<TreeNode>();
         q.add(searchRoot);

         int steps = 0;

         Set<TreeNode> visited = new HashSet<TreeNode>();


          //TODO two level loops, tmpQ in inner loop
        while (!q.isEmpty() && steps <= K) {

             Queue<TreeNode> tmpQ = new LinkedList<TreeNode>();

             while (!q.isEmpty()) {

                 if(steps == K){
                     //TODO list addAll, collections
                     result.addAll(q.stream().map( k -> k.val).collect(Collectors.toList()));
                     break;
                 }

                 TreeNode next = q.poll();
                 visited.add(next);


                 for (TreeNode node : nodes.get(next)) {
                     //TODO add only if not visisted
                     if (!visited.contains(node)) {
                         tmpQ.add(node);
                     }

                 }
             }

             steps++;
             q.addAll(tmpQ);


         }
         return result;
     }




    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {

        Map<TreeNode, List<TreeNode>> graphMap = new HashMap<TreeNode, List<TreeNode>>();
         buildToGrah(root, graphMap);

        return searchNode(target,K,graphMap)   ;
    }

}