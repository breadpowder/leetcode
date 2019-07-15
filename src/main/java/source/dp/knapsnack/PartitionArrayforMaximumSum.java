package source.dp.knapsnack;

import java.lang.reflect.Array;
import java.util.stream.IntStream;

public class PartitionArrayforMaximumSum {

    public int maxSumAfterPartitioning(int[] A, int K) {

        //dp[j] -> maxValue after partition from 1 to j

        //how about j+1?

        // 2, 9 5, 10

        int dp[] = new int[A.length+1];



        //dp[0] = A[0];

        for(int i=1; i<=A.length; i++){

            int value = dp[i-1] + A[i-1];
            int max = A[i-1];
            for(int k=i; k>=Math.max(i-K+1, 1) ; k--){

                max = Math.max(A[k-1], max);
                value = Math.max(value, dp[k-1] + max * (i-k+1));
            }

            //System.out.println(i-1 + ", " + value);
            dp[i] = value;
        }


        //IntStream.of(dp).forEach(i -> System.out.print(i + ","));
        return dp[A.length];

    }


    public static  void  main(String args[]){

       PartitionArrayforMaximumSum testee = new PartitionArrayforMaximumSum();
       testee.maxSumAfterPartitioning(new int[]{1,15,7,9,2,5,10}, 3);

    }


}
