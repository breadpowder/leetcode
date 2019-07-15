package source.dp.knapsnack;

/*
1049. Last Stone Weight II
Medium

157

5

Favorite

Share
We have a collection of rocks, each rock has a positive integer weight.

Each turn, we choose any two rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:

If x == y, both stones are totally destroyed;
If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
At the end, there is at most 1 stone left.  Return the smallest possible weight of this stone (the weight is 0 if there are no stones left.)



Example 1:

Input: [2,7,4,1,8,1]
Output: 1
Explanation:
We can combine 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,
we can combine 7 and 8 to get 1 so the array converts to [2,1,1,1] then,
we can combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we can combine 1 and 1 to get 0 so the array converts to [1] then that's the optimal value.
 */

//

public class LastStoneWeightII {
    public int lastStoneWeightII(int[] stones) {

        //dp[i][j] is the smallest of destory between i, j
        //dp[i][j+1] j and for len in (1, j-i+1)

       /* int dp[][] = new int[stones.length][stones.length];

            for(int len=1; len<stones.length; len++)
                for(int i=0; int i<=stones-1; i++){

                    int j = i + len -1;

                 for(int k=1; k<stones.length; len++)
                {


                    dp[i][j] = dp[i][k] +  dp[k+1][j-1]

                }
                }

    }*/

        //TODO StreamOf too slow, a magniute slow
        //int sum = IntStream.of(stones).sum();

        int sum =0;
        for(int s: stones){

            sum += s;
        }

        int[] dp = new int[(sum>>1)+1];

        dp[stones[0]] = 1;
        dp[0] = 1;

        for(int i=1; i<stones.length; i++)  {
            for(int j=sum/2; j>=stones[i]; j--){

                if(dp[j] == 1 || dp[j - stones[i]] == 1) dp[j] =1;

            }
        }


        for(int s = sum/2; s>=0; s--){
            if(dp[s]==1) return sum - s- s;
        }

        return sum;
    }

}
