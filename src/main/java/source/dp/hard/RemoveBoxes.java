package source.dp.hard;

/*
546. Remove Boxes

Given several boxes with different colors represented by different positive numbers.
You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
Find the maximum points you can get.

Example 1:
Input:

[1, 3, 2, 2, 2, 3, 4, 3, 1]
Output:
23
Explanation:
[1, 3, 2, 2, 2, 3, 4, 3, 1]
----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
----> [1, 3, 3, 3, 1] (1*1=1 points)
----> [1, 1] (3*3=9 points)
----> [] (2*2=4 points)
Note: The number of boxes n would not exceed 100.

 */


import java.util.LinkedList;
import java.util.List;

public class RemoveBoxes {

    public int removeBoxes(int[] boxes) {

        //dp[i][j][k] denote number of k following index j say dfs(ababca) = dfs(aba|a) + dfs(bc)
        //d[[i][j-1][k] =  dp[i][j-1][0] + (k+1)^2
        // or  = dp[i][p][k+1] + dp[p+1][j-1][]

        int dp[][][] = new int[101][101][101];

        StringBuilder sb = new StringBuilder();


        return  score(dp, boxes, 0, boxes.length-1, 0);

    }


    public int score(int dp[][][], int[] boxes, int i, int j, int k){

        if(i>j) return 0;

        if(dp[i][j][k]!=0) return dp[i][j][k];

        dp[i][j][k] = score(dp, boxes, i, j-1, 0) + (k+1)*(k+1);

        for(int p=j-1; p>=i; p--){
            if(boxes[p] == boxes[j])
                dp[i][j][k] = Math.max(dp[i][j][k], score(dp, boxes, i, p, k + 1) + score(dp, boxes, p + 1, j-1, 0));
        }

        return dp[i][j][k];
    }


    public int scoreOPTIMIZED(int dp[][][], int[] boxes, int i, int j, int k){

        if(i>j) return 0;

        if(dp[i][j][k]!=0) return dp[i][j][k];

        dp[i][j][k] = score(dp, boxes, i, j-1, 0) + (k+1)*(k+1);

        int p = j-1;

        while(p>=i) {

            if(boxes[p] == boxes[j]){
                int count = 1;

                while(p>i && boxes[p-1] == boxes[j]){
                    count ++;
                    p--;
                }

                dp[i][j][k] = Math.max(dp[i][j][k], score(dp, boxes, i, p, k + count) + score(dp, boxes, p + count, j-1,0));

            }

            p--;
        }

        return dp[i][j][k];
    }

}
