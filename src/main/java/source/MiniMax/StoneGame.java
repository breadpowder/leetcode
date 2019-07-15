package source.MiniMax;
//877
/*
Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].

The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.

Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.
  This continues until there are no more piles left, at which point the person with the most stones wins.

Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.


Example 1:

Input: [5,3,4,5]
Output: true
Explanation:
Alex starts first, and can only take the first 5 or the last 5.
Say he takes the first 5, so that the row becomes [3, 4, 5].
If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
This demonstrated that taking the first 5 was a winning move for Alex, so we return true.


Note:

2 <= piles.length <= 500
piles.length is even.
1 <= piles[i] <= 500
sum(piles) is odd.
 */

//take away

//this is wrong redo
public class StoneGame {


    public boolean stoneGame(int[] piles) {


        int N= piles.length;
        int[][] scores = new int[N][N];


        return rec(piles, 0 , N-1,scores)> 0;

    }


    public int rec(int[] piles, int start, int end, int[][] score) {

        if(start==end) return piles[start];

        if(score[start][end]==0)


        {
            score[start][end] = Math.max(piles[start] - rec(piles, start+1, end,score),

                    piles[end] - rec(piles, start, end-1,score)
            );

        }


        return score[start][end];


    }


    /*
    public boolean stoneGame(int[] piles) {

         int WIN = IntStream.of(piles).sum()/2;

         Map<String,Integer> marker = new HashMap<String,Integer>();

         return canWin(piles, 0, piles.length-1, 0, 0, WIN, marker);
    }


    public boolean canWin(int[] piles, int start, int end, int sum, int totalNow, int WIN, Map<String,Integer> marker){

        String status = String.valueOf(start) + ":" + String.valueOf(end);

       if(marker.containsKey(status)) return marker.get(status) == 1;

         if(start==end)  {
             if(sum + piles[start] > WIN){

                    marker.put(status, 1);
                   return true;
             }


            marker.put(status, -1);
            return false;

         }

         if(sum > WIN ) {

             marker.put(status, 1);
             return true;

         }


        if(!canWin(piles, start+1, end, totalNow - sum ,totalNow + piles[start],  WIN, marker) || !canWin(piles, start, end-1, totalNow -  sum, totalNow + piles[end], WIN,marker)){

            marker.put(status, 1);

            return true;

        }

        marker.put(status, -1);

        return false;



    }*/
}
