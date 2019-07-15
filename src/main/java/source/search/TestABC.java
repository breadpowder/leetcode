package source.search;


import scala.Int;

import java.util.*;

public class TestABC {


    public static int rightMinimal(int number, List<Integer> cost, boolean[] used){

        for(int i=0; i< cost.size(); i++){

                if(used[i]) continue;



        }

        return -0;

    }

    public static int minimalCost(int number, List<Integer> costs){

        

        Collections.sort(costs);
            int[][] dp = new int[number][number];


            int[] sum = new int[number];

            for(int i=0; i<number-1; i++){

                sum[i+1] = sum[i] + costs.get(i+1);
            }


            for(int len=2; len<=number; len++)

                for(int i=0; i<= number - len; i++) {

                    int j = i + len - 1;

                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j]);

                    }

                    dp[i][j] += sum[j] - sum[i] + costs.get(i);
                }

            return dp[0][number-1];


    }


    public static void main(String[] args){

        System.out.println(minimalCost(4, Arrays.asList(3,3,4,4)));

    }
}
