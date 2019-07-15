package source.dp;

/*
688. Knight Probability in Chessboard

Share
On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.


Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.

The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.



Example:

Input: 3, 2, 0, 0
Output: 0.0625
Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.


Note:

N will be between 1 and 25.
K will be between 0 and 100.
The knight always initially starts on the board.

 */

//take away dp[K][i][j] = 1/8 * SUM(dp[K-1][previous_i][previous_j])
public class KnightProbablityInChess {

    public double knightProbability(int N, int K, int r, int c) {
        double[][][] dp = new double[K+1][N][N];

        dp[0][r][c] = 1;

        int[] steps = {2,1,-2,-1,-2,1,2,-1,2};

        for(int k=1; k<=K; k++){

            for(int i=0; i<N; i++)
                for(int j=0;j<N; j++)
                    for(int s=0; s<8; s++)
                    {

                        int p_i = i + steps[s];
                        int p_j = j + steps[s+1];

                        if((p_i>=0 && p_i<N) && (p_j>=0 && p_j<N) && dp[k-1][p_i][p_j]>0)
                            dp[k][i][j] += dp[k-1][p_i][p_j] * 0.125 ;


                    }
        }

        double result = 0;

        for(int i=0; i<N; i++)
            for(int j=0;j<N; j++){

                result += dp[K][i][j];
            }

        return result;
    }
}
