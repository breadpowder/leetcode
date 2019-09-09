package source.dp;

import java.lang.reflect.Array;

//
// 1130.Given an array arr of positive integers, consider all binary trees such that:
//
//Each node has either 0 or 2 children;
//The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
//The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
//Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.
//
//
//
//Example 1:
//
//Input: arr = [6,2,4]
//Output: 32
//Explanation:
//There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.
//
//    24            24
//   /  \          /  \
//  12   4        6    8
// /  \               / \
//6    2             2   4
//
//
//Constraints:
//
//2 <= arr.length <= 40
//1 <= arr[i] <= 15
//It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).

//Take away: see the error marked in TODO
public class MinimumCostTreeFromLeafValues {

    public int mctFromLeafValues(int[] arr) {

        //dp denotes the number of the mininum sum


        int dp[][] = new int[arr.length][arr.length];

        /*for(int i=0; i<arr.length; i++){
            dp[i][i] = 0;
        }*/

        for(int len=2; len<=arr.length; len++)
            for(int i=0; i<= arr.length-len; i++){

                int j = i + len -1;
                int max_left = arr[i];
                int max_right = arr[j];

                dp[i][j] = Integer.MAX_VALUE;


                int max[] = new int[j+1];
                for(int m=j; m>i; m--){

                    //TODO check error here, need to keep update max_right
                    max_right  = Math.max(max_right, arr[m]);
                    max[m] = max_right;
                }


                for(int k=i; k<j; k++){

                    max_left = Math.max(arr[k], max_left);

                    //TODO max_right is k
                    max_right = max[k+1];


                    dp[i][j] = Math.min(dp[i][k]+dp[k+1][j] + max_left * max_right, dp[i][j]);

                    System.out.println("len=" + len + ", i=" + i + ",j=" + j + ",k=" + k + ",dp[i][k]=" + dp[i][k]+ ",dp[k+1][j]=" + dp[k+1][j] + ",max_left=" + max_left + ",max_right=" + max_right + ", dp[i][j]=" + dp[i][j]);


                }


            }


        return dp[0][arr.length-1];

    }

    public static void main(String[] args) {
        MinimumCostTreeFromLeafValues testee = new MinimumCostTreeFromLeafValues();

        System.out.println(testee.mctFromLeafValues(new int[]{6,2,4,3}));
    }
}
