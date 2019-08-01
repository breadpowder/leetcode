package source;

/*
787. Cheapest Flights Within K Stops
Medium

There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input:
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation:
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.

Example 2:
Input:
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation:
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.

Note:

    The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
    The size of flights will be in range [0, n * (n - 1) / 2].
    The format of each flight will be (src, dst, price).
    The price of each flight will be in the range [1, 10000].
    k is in the range of [0, n - 1].
    There will not be any duplicated flights or self cycles.


 */

//take away: the subproblem is dp[target][k] not dp[src][dst][k], see bellman ford
public class CheapFlightWithKStop {

    //shortest path in a K node path
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

        int[][] dp = new int[n][K + 1];

        int[][] edges = new int[n][n];


        for (int j = 0; j < n; j++)
            for (int k = 0; k <= K; k++)
                dp[j][k] = Integer.MAX_VALUE;

        for (int[] flight : flights) {
            edges[flight[0]][flight[1]] = flight[2];
            if(flight[0]==src)
            {
                dp[flight[1]][0] = flight[2];
            }
        }

        for (int k = 1; k <= K; k++) {
            for (int i = 0; i < n; i++) {
                dp[i][k] = dp[i][k-1];

                for(int j=0; j<n; j++)
                {
                    if (i == j || edges[j][i] == 0 || dp[j][k - 1] == Integer.MAX_VALUE) continue;
                    dp[i][k] = Math.min(dp[i][k], dp[j][k - 1] + edges[j][i]);
                    //System.out.println("dp[k-1]:" + "," + dp[i][k - 1] + ", edges:" + i + "," + edges[i][dst]);
                }
            }

            //System.out.println("k:" + k + "," + dp[dst][k]);
        }


        if (dp[dst][K] == Integer.MAX_VALUE) return -1;
        return dp[dst][K];
    }

}
