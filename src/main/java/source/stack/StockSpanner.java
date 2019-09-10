package source.stack;

/* 901
Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's price for the current day.

The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backwards) for which the price of the stock was less than or equal to today's price.

For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85], then the stock spans would be [1, 1, 1, 2, 1, 4, 6].

Example 1:

Input: ["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
Output: [null,1,1,1,2,1,4,6]
Explanation:
First, S = StockSpanner() is initialized.  Then:
S.next(100) is called and returns 1,
S.next(80) is called and returns 1,
S.next(60) is called and returns 1,
S.next(70) is called and returns 2,
S.next(60) is called and returns 1,
S.next(75) is called and returns 4,
S.next(85) is called and returns 6.

Note that (for example) S.next(75) returned 4, because the last 4 prices
(including today's price of 75) were less than or equal to today's price.


Note:

Calls to StockSpanner.next(int price) will have 1 <= price <= 10^5.
There will be at most 10000 calls to StockSpanner.next per test case.
There will be at most 150000 calls to StockSpanner.next across all test cases.
The total time limit for this problem has been reduced by 75% for C++, and 50% for all other languages.


Please read the solution here:
https://www.youtube.com/watch?v=RGRC46zHB98&list=PLLuMmzMTgVK7vEbeHBDD42pqqG36jhuOr&index=18

 */

public class StockSpanner {

    int[] dp =  new int[10000];

    int[] arr = new int[10000];

    int idx = 0;

    public StockSpanner() {



    }

    public int next(int price) {

        if(idx==0) dp[0]=1;

        else{

            if(price<arr[idx-1]) dp[idx]=1;

            else if(price==arr[idx-1]) dp[idx] = dp[idx-1] + 1;


            else{

                int i = idx -1;
                while(i>=0 && arr[i]<=price){

                    dp[idx] += dp[i];

                    i = i - dp[i];

                }

                dp[idx] +=1;
            }


        }
        arr[idx] = price;

        idx ++;

        return dp[idx-1];


    }
}
