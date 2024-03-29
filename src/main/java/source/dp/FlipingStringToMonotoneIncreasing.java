package source.dp;

/*
926. Flip String to Monotone Increasing
Favorite

Share
A string of '0's and '1's is monotone increasing if it consists of some number of '0's (possibly 0), followed by some number of '1's (also possibly 0.)

We are given a string S of '0's and '1's, and we may flip any '0' to a '1' or a '1' to a '0'.

Return the minimum number of flips to make S monotone increasing.



Example 1:

Input: "00110"
Output: 1
Explanation: We flip the last digit to get 00111.
Example 2:

Input: "010110"
Output: 2
Explanation: We flip to get 011111, or alternatively 000111.
Example 3:

Input: "00011000"
Output: 2
Explanation: We flip to get 00000000.

 */

//TAKE AWAY:

//1. JAVA FOR CONDITION for(int i = ; <when satifsying condtion>

//2.  condition dp[i][1]

import java.util.Arrays;

public class FlipingStringToMonotoneIncreasing {

    public int minFlipsMonoIncr(String s) {

            if(s==null || s.length()==0) return 0;

            int[][] dp = new int[s.length()][2];

           // Arrays.fill(dp, Integer.MIN_VALUE);

            dp[s.length()-1][0] = s.charAt(s.length()-1)== '0' ? 0:1;
            dp[s.length()-1][1] = s.charAt(s.length()-1)== '1' ? 0:1;


            for(int i=s.length()-2; i>=0; i--)
                    for(int j=0; j<2; j++){
                        if(j==0) {
                            dp[i][0] = Math.min(dp[i+1][0], dp[i+1][1])+ (s.charAt(i) == '0' ? 0:1);
                        }

                        if(j==1){
                            //String str= s.substring(i+1);
                            dp[i][1] = dp[i+1][1] + (s.charAt(i) == '1' ? 0:1); //str.length() - str.replace("0", "").length()) + (s.charAt(i) == '1' ? 0:1);
                        }
            }

            return  Math.min(dp[0][0], dp[0][1]);
    }
}
