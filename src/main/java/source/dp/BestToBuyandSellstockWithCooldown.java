package source.dp;

/*
309
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
 */

public class BestToBuyandSellstockWithCooldown {

    public int maxProfit(int[] prices) {


        // states:

        // hold (i.e after buy), cooldown (immediately afterSell), nothold (afterSell but not coolDown)

        if(prices.length==0) return 0;

        int[] dp = new int[3];

        dp[0] = -prices[0];

        dp[1] = Integer.MIN_VALUE;

        dp[2] = 0;


        for(int i=1; i<prices.length; i++){

            int hold = Math.max(dp[0], dp[2]- prices[i]);

            int coolDown = dp[0] + prices[i];

            int notHold = Math.max(dp[2], dp[1]);

            dp[0] = hold;

            dp[1] = coolDown;

            dp[2] = notHold;


        }

        return Math.max(Math.max(dp[0],dp[1]),dp[2]);

    }
}
