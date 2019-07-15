package source.dp;

/*
714
Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.

You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

Return the maximum profit you can make.

Example 1:
Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
Buying at prices[0] = 1
Selling at prices[3] = 8
Buying at prices[4] = 4
Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.

 */

//take away, review together with 309, WITHCOLLING DOWN, i did not figure out the right states at the first place

public class BestTimetoBuySellStockwithTransactionFee {

    public int maxProfit(int[] prices, int fee) {


        //state hold, notHold,


        int[] dp = new int[2];

        dp[0] = -prices[0];
        dp[1] = 0;


        // hold[0] = -50001;
        //TODO disallow sell before buy
        for(int i=1; i<prices.length; i++){

            int hold = Math.max(dp[0], dp[1] - prices[i]);

            int notHold = Math.max(dp[0] + prices[i] - fee, dp[1]);

            dp[0] = hold;

            dp[1] = notHold;

            // System.out.println(dp[0] + "," + dp[1]);
        }


        return Math.max(dp[0], dp[1]);
    }
}
