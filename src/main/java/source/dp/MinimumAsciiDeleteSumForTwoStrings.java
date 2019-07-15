package source.dp;

/*
712
Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.

Example 1:
Input: s1 = "sea", s2 = "eat"
Output: 231
Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
Deleting "t" from "eat" adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
Example 2:
Input: s1 = "delete", s2 = "leet"
Output: 403
Explanation: Deleting "dee" from "delete" to turn the string into "let",
adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
Note:

0 < s1.length, s2.length <= 1000.
All elements of each string will have an ASCII value in [97, 122].
 */

//take away, need padding, because 'ac' CompareTest with 'c'
//dp[i][j]: s1 [-1,i) CompareTest with [-1,j), dp[0][0] CompareTest nothing, dp[0][1]
//see code clean up

public class MinimumAsciiDeleteSumForTwoStrings {

    public int minimumDeleteSum(String s1, String s2) {

        if((s1 == null || s1.length()==0) && (s2==null && s2.length()==0)) return 0;

        int[][] dp = new int[s1.length()+1][s2.length()+1];

        //dp[0][0] = 0;

        for (int i = 1; i <= s2.length(); i++){
            dp[0][i] = dp[0][i-1] +  s2.charAt(i-1) ;
        }

        for (int j = 1; j <= s1.length(); j++){
            dp[j][0] = dp[j-1][0] +  s1.charAt(j-1) ;
        }

        for (int i = 1; i <= s1.length(); i++)
            for(int j=1; j<= s2.length(); j++){

                int min = Integer.MAX_VALUE;
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    min = Math.min(min, dp[i-1][j-1]);
                }

                //remove this
           /*     else{
                    min = Math.min(min, dp[i-1][j-1] + s1.charAt(i-1)  + s2.charAt(j-1));
                }*/

                else {
                    dp[i][j] = Math.min(Math.min(min, dp[i - 1][j] + s1.charAt(i - 1)), dp[i][j - 1] + s2.charAt(j - 1));
                }


        }

        return dp[s1.length()][s2.length()];
    }
}
