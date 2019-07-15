package source.dp;

/*
801. Minimum Swaps To Make Sequences Increasing

We have two integer sequences A and B of the same non-zero length. We are allowed to swap elements A[i] and B[i].
Note that both elements are in the same index position in their respective sequences.

At the end of some number of swaps, A and B are both strictly increasing. (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)
Given A and B,return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that the given input always makes it possible.

Example:
Input: A = [1,3,5,4], B = [1,2,3,7]
Output: 1
Explanation:
Swap A[3] and B[3].  Then the sequences are:
A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
which are both strictly increasing.

Note:

A, B are arrays with the same length, and that length will be in the range [1, 1000].
A[i], B[i] are integer values in the range [0, 2000].
 */

public class MinimumSwapsToMakeSequencesIncreasing {

    public int minSwap(int[] A, int[] B) {

        //dp[i][0-1] 0 not swapped

        int[][] dp = new int[A.length][2];


        /*for(int i=0; i<A.length; i++){

            dp[i][0] = dp[i][1] = 1001;
        }*/

        dp[0][0] = 0;

        dp[0][1] = 1;


        for(int i=1; i<A.length; i++){

            if(A[i]>A[i-1] && B[i]>B[i-1])

            {

                //TODO this condtion happens at the same time, e.g [10,11] [9,10] , this cannot be switched
                dp[i][0] =  A[i] > B[i-1]  &&   B[i] >A [i-1]  ? Math.min(dp[i-1][1], dp[i-1][0]): dp[i-1][0];

                dp[i][1] = (A[i] > B[i-1] &&   B[i] >A [i-1]  ? Math.min(dp[i-1][1], dp[i-1][0])  : dp[i-1][1]) + 1;


            }

            else{
                dp[i][0] = dp[i-1][1];

                dp[i][1] = dp[i-1][0] + 1;
            }
        }


        return Math.min(dp[A.length-1][0], dp[A.length-1][1]);

    }

    public static void main(String[] args){

        MinimumSwapsToMakeSequencesIncreasing testee = new MinimumSwapsToMakeSequencesIncreasing();

        System.out.println(testee.minSwap(new int[]{0,4,4,5,9}, new int[]{0,1,6,8,10}));

    }
}
