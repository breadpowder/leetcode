package source.dp.knapsnack;

/*

518. Coin Change 2
Medium

765

38

Favorite

Share
You are given coins of different denominations and a total amount of money.
 Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.



Example 1:

Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:3

Input: amount = 10, coins = [10]
Output: 1


 */


import java.util.Arrays;

//take away:
//1->2 and 2->1 are different, the first version ensure the coin arrnage from big to small, i.e. 2,1 ->3 not 1,2 goes to 3

//see https://www.youtube.com/watch?v=ZKAILBWl08g&t=827s
//  DP method:
// 1)dp[i][j] = dp[i-1][j] + dp[i-1][j-coins[i]] + dp[i-1][j-2*coins[i]] ... + dp[i-1][j-m*coins[i]]
//  2) dp[i][j-coins[i]] = dp[i-1][j-coins[i]] ... + dp[i-1][j-m*coins[i]]


// Space optimization: dp[j] = dp[j] + dp[j-coins[i]]
public class CoinChange2 {

    public int change(int amount, int[] coins) {

        //this is the base case
        if(amount==0) return 1;

        coins= Arrays.stream(coins).filter(c -> c<=amount).toArray();

        int len = Math.max(amount,coins.length);

        int[][] dp = new int[len+1][len+1];

        //Tthis is the base case

        for(int coin: coins){

            dp[coin][coin] = 1;
        }

        for(int i=1; i<=amount; i++)
            for(int coin: coins)
                for(int c: coins){
                    if(i+coin<=amount && c<=coin){
                        dp[i+coin][coin] += dp[i][c];
                        // System.out.println("i + coin:" + i+coin + "," + dp[i+coin]);
                    }
                }

        int sum =0;
        for(int coin:coins)
        {
            sum += dp[amount][coin];
        }
        return sum;

    }
}
