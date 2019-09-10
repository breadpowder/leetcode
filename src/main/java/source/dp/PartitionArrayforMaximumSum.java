package source.dp;

/*
1043. Partition Array for Maximum Sum
Medium

Share
Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.
After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning.


Example 1:

Input: A = [1,15,7,9,2,5,10], K = 3
Output: 84
Explanation: A becomes [15,15,15,9,10,10,10]


Note:

1 <= K <= A.length <= 500
0 <= A[i] <= 10^6

 */

public class PartitionArrayforMaximumSum {

    public int maxSumAfterPartitioning(int[] A, int K) {

        //Solution: 1) problem dp[j] means maxValue to index j 2) for j+1 try the last partition for j+1 from j+1 - k+1?  3)base case dp[0]= A[0]


        int dp[] = new int[A.length+1];

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
