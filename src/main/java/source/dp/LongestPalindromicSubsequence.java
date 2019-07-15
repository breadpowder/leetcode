package source.dp;

/*
516 Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".
Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".
 */

//TODO speed is not fast only 30% with ram 10%, need to look at sample solution

public class LongestPalindromicSubsequence {

    public int longestPalindromeSubseq(String S) {

        int[][] dp = new int[S.length()][S.length()];

       /* for(int[] row: dp){
            Arrays.fill(row,1);
        }*/


        //dp[i][j] = dp[i-1][j-1] +

        for(int len=1; len <= S.length(); len ++)


            for(int i=0; i<=S.length() -len; i++){

                int j = i + len -1;

                if(i==j) dp[i][i] =1;


                else if(S.charAt(i)==S.charAt(j))
                {
                    dp[i][j] = i+1 < j ? dp[i+1][j-1] + 2 : 2;
                }

                else{

                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }


        return dp[0][S.length()-1];

    }
}
