package source.tree;

import java.util.*;

/*
310. Minimum Height Trees
Medium

For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1 :

Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3

Output: [1]

Example 2 :

Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5

Output: [3, 4]

Note:

    According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
    The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.


 */
public class MinimumHeightTree {


    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        // a - b - e -  c - d
        // find the middle int he longest path

        List<Integer>[] adj = new LinkedList[n];

        for(int[] edge: edges){

            if(adj[edge[0]] == null){
                adj[edge[0]] = new LinkedList<Integer>();
            }


            if(adj[edge[1]] == null){
                adj[edge[1]] = new LinkedList<Integer>();
            }

            adj[edge[0]].add(Integer.valueOf(edge[1]));
            adj[edge[1]].add(Integer.valueOf(edge[0]));
        }


        boolean[] removed = new boolean[n];
        int count = n;

        Queue<Integer> q = new LinkedList<Integer>();

        while(count!=1 & count!=2) {

            //find leaf
            for(int i=0; i< n; i++ ){
                if(!removed[i] && adj[i].size()==1){
                    q.add(i);
                    removed[i] = true;
                    count --;
                }
            }
            // update edges
            while(!q.isEmpty()){

                   int v1 = q.poll();
                   adj[v1] = null;

                   int v2 =  adj[v1].get(0);
                   int idx  = adj[v2].indexOf(v1);
                   adj[v2].remove(idx);
             }
        }

        List<Integer> result = new LinkedList<Integer>();

        for(int i=0; i < n; i++){
            if (!removed[i]) result.add(i);
        }

        return  result;

    }


}
