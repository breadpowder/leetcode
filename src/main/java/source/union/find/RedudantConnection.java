package source.union.find;

/*
684
 In this problem, a tree is an undirected graph that is connected and has no cycles.

The given input is a graph that started as a tree with N nodes
(with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N,
 and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

Example 1:

Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3

Example 2:

Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3

Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.


 */

public class RedudantConnection {

    public int[] findRedundantConnection(int[][] edges) {

        int[] result = null;

        int[] un = new int[edges.length+1];

        for(int i=0; i<un.length; i++){

            un[i] = i;
        }


        for(int[] edge: edges){

            int u = edge[0];
            int v = edge[1];

            if(isConnected(u, v, un)){
                result = edge;
            }

            else{
                union(u, v, un);
            }
        }


        return result;

    }


    void union(int i, int j, int[] u){

        int root_i = root(i, u);
        int root_j = root(j, u);

        u[root_i] = root_j;
    }

    int root(int i, int[] u){
        while(i!=u[i]){
            u[i] = u[u[i]];
            i = u[i];
        }

        return i;
    }

    boolean isConnected(int i, int j,int[] u){

        return root(i, u) == root(j, u);
    }
}
