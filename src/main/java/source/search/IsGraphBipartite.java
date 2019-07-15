package source.search;
/*

785

Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation:
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation:
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.

 */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

//take away //connected components: if multiple componentes, forgot about it

//too slow, see youtube https://www.youtube.com/watch?v=zlINFZ123ec

public class IsGraphBipartite {

    public boolean isBipartite(int[][] graph) {

        if (graph.length==0) return false;

        //algorithm: hmm, subcomponents?

        // 1.  initial first adj list, biparite

        int[] identity = new int[graph.length];

        Queue<Integer> q = new LinkedList<Integer>();

        int marker = 1;

        // 2. extend the adj list thorugh bfs, add to the other part, if a node adds both part return false
        //int root = findNextRoot(graph, identity);

        //while(root != Integer.MAX_VALUE){

        //Stack<Integer> s = new Stack<>();

        //Queue<Integer> q = null;




        for(int i=0;  i< graph.length ; i ++) {

            if (identity[i] == 0 && graph[i].length != 0)
                q.add(i);

            // TODO can use a size count to optimize

            while (!q.isEmpty()) {
                int size = q.size();
                while (size != 0) {

                    // List<Integer> tmp =  new LinkedList<Integer>();

                    Integer node = q.poll();
                    identity[node] = marker;
                    for (int adj : graph[node]) {

                        if (identity[adj] == marker) {
                            return false;
                        }

                        //Only add visited
                        if (identity[adj] == 0) {
                            q.add(adj);
                        }

                    }
                    size--;

/*                while(!q.isEmpty()){
                    Integer node = q.poll();
                    identity[node] = marker;
                    tmp.add(node);
                }


                for(Integer node: tmp){
                    int[] adjs = graph[node];
                    for(int adj: adjs){

                        if(identity[adj] == marker){
                            return false;
                        }

                        //Only add visited
                        if(identity[adj]==0){
                            q.add(adj);
                        }

                    }

                }*/

                    marker = -marker;
                }
                //root =  findNextRoot(graph, identity);
            }
        }
        // 3. return true
        return true;

    }


    // TODO this is much mor faster, why?
    public boolean isBipartiteDFS(int[][] graph) {


       int[] visited = new int[graph.length];


        for(int i=0;  i<graph.length; i++){
            //TODO new componeents
            if(visited[i] == 0 && !dfsRec(graph, i, 1, visited)){
                return  false;
            }
        }

        return true;

    }


    public boolean dfsRec(int[][] graph, int node, int color, int[] visited) {

        if(visited[node] !=0 && visited[node] != color){
            return  false;
        }

        if(visited[node]==0) {
            visited[node] = color;

            int[] adjs = graph[node];
            for (int adj : adjs) {
                if(!dfsRec(graph, adj, -color, visited)){
                    return  false;
                }
            }
        }

        return true;


    }





/*    public int findNextRoot(int[][] graph, int[] identity){

        for(int i=0; i<graph.length; i++){
            if(graph[i].length!=0 && identity[i]==0)
            {
                return i;
            }
        }

        return Integer.MAX_VALUE;
    }*/
}
