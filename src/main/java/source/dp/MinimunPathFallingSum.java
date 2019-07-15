package source.dp;
/*
931. Minimum Falling Path Sum
Medium

273

31

Favorite

Share
Given a square array of integers A, we want the minimum sum of a falling path through A.

A falling path starts at any element in the first row, and chooses one element from each row.  The next row's choice must be in a column that is different from the previous row's column by at most one.



Example 1:

Input: [[1,2,3],[4,5,6],[7,8,9]]
Output: 12
Explanation:
The possible falling paths are:
[1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
[2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
[3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
The falling path with the smallest sum is [1,4,7], so the answer is 12.



Note:

1 <= A.length == A[0].length <= 100
-100 <= A[i][j] <= 100
 */
public class MinimunPathFallingSum {

    public int[] next = new int[]{-1, 0, 1};

    public int minFallingPathSum(int[][] A) {

        //dp[i][j] is minimal sum to ith row, and jth column

        //dp[i+1][j] = Math.min(dp[i][j] + A[i+1][j], dp

        if(A.length==0) return 0;

        int dp[][] = new int[A.length+2][A[0].length+2];

        for(int i=1; i<=A.length; i++){



            for(int j=1; j<=A[0].length; j++){

                //TODO edge case, i.e column is 0 or length is wrong

                dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i-1][j-1]), dp[i-1][j+1]) + A[i-1][j-1];

                // System.out.println(i + "," + j + "v:" + dp[i][j]);

            }

            //TODO EDGE CASE
            dp[i][0] = dp[i][1];
            dp[i][A[0].length+1] = dp[i][A[0].length];
        }


        int min = Integer.MAX_VALUE;

        for(int i=1; i<= A[0].length; i++){

            min = Math.min(dp[A.length][i], min);
        }

        return min;
    }
    /*

    //TODO time exceed 3^n limit reached
    public int minFallingPathSum(int[][] A) {

        int[] result = new int[1];
        result[0] = Integer.MAX_VALUE;

        for(int i=0; i<A[0].length; i++){
            pathSum(A, 0, result, 0, i);
        }

        return result[0];

    }


    public void pathSum(int[][] A, int sum, int[] min, int row, int idx){

        if(idx<0 || idx==A[0].length) return;

        if(row==A.length && sum<min[0]) min[0] = sum;

        //TODO minus is wrong here since minus can decreae the number
        if(row ==A.length && sum>= min[0]) return;

        sum = sum + A[row][idx];

        for(int n: next){
                 pathSum(A, sum, min, row+1, idx + n);
            }

        return ;

    }*/
}

