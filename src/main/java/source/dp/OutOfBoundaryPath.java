package source.dp;
/*
576
There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball,


you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However,
you can at most move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.



Example 1:

Input: m = 2, n = 2, N = 2, i = 0, j = 0
Output: 6
Explanation:

Example 2:

Input: m = 1, n = 3, N = 3, i = 0, j = 1
Output: 12
Explanation:

 */

//take away
// //intial state should be 1 not 0,otherwise, all avalues will be 0

public class OutOfBoundaryPath {

    public int findPaths(int m, int n, int N, int i, int j) {

        long [][][] dp=new long[m+2][n+2][N+1];

        int mod = 1000000007;

        dp[i+1][j+1][0]=1;

        int[] steps = {0,-1,0,1,0};


        for(int k=1;k<=N;k++)
            for(int r=0;r<m+2; r++)
                for(int c=0;c<n+2;c++)
                    for(int s=0;s<4;s++)
                    {
                        int r_idx= r + steps[s];
                        int c_idx = c + steps[s+1];
                        if((r_idx>0 && r_idx<m+1) && (c_idx>0 && c_idx<n+1))
                            dp[r][c][k] = (dp[r][c][k] + dp[r_idx][c_idx][k-1]) % mod;
                    }

        long sum = 0;

        for(int c=0; c<n+2; c++)
            for(int k=1; k<=N; k++)
            {
                sum += dp[0][c][k] + dp[m+1][c][k];
            }

        for(int r=0; r<m+2; r++)
            for(int k=1; k<=N; k++)
            {
                sum += dp[r][0][k] + dp[r][n+1][k];
            }

        return (int)(sum % mod);

    }

    public static void main(String[] args) {

        OutOfBoundaryPath testee = new OutOfBoundaryPath();
        //int result = testee.findPaths(2,2,2, 0, 0);

        int result = testee.findPaths(1,3,3, 0, 1);

        System.out.println(result);
    }
}
