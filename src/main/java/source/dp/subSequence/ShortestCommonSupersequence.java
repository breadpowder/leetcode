package source.dp.subSequence;

/*
1092. Shortest Common Supersequence
Hard

121

6

Favorite

Share
Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.  If multiple answers exist, you may return any of them.

(A string S is a subsequence of string T if deleting some number of characters from T (possibly 0, and the characters are chosen anywhere from T) results in the string S.)



Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation:
str1 = "abac" is a substring of "cabac" because we can delete the first "c".
str2 = "cab" is a substring of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.


Note:

1 <= str1.length, str2.length <= 1000
str1 and str2 consist of lowercase English letters.
 */

//TODO review two places and how about construct on the way
public class ShortestCommonSupersequence {

    public String shortestCommonSupersequence(String str1, String str2) {

        //dp[i][j] longest subsequence prefix str1[...i] amd

        //Longest commond subsequence dp[i][j]
        int dp[][] = new int[str1.length()+1][str2.length()+1];

        for(int i=1; i<=str1.length(); i++)
            for(int j=1; j<=str2.length(); j++) {
                int k = Math.max(dp[i][j - 1], dp[i - 1][j]);

                //TODO can it be simplied?
                dp[i][j] = str1.charAt(i - 1) == str2.charAt(j - 1) ? Math.max(k, dp[i - 1][j - 1] + 1) : k;

            }


        //backward search dp[i][j]
        int i = str1.length();
        int j = str2.length();


        StringBuilder sb = new StringBuilder();

        while(i!=0 || j!=0){

            //System.out.println("i=" + i  +",j=" + j);
            char ch = '\0';

            if(i == 0)  ch = str2.charAt(--j);

            else if(j==0) ch = str1.charAt(--i);

            else if(str1.charAt( i-1) == str2.charAt(j-1)) {
                ch = str1.charAt(i-1);
                i--; j--;
            }

            else if(dp[i-1][j] == dp[i][j]) {
                ch = str1.charAt(--i);
            }

            else if(dp[i][j-1] == dp[i][j]) {
                ch = str2.charAt(--j);
            }

            sb.append(ch);
        }

        return sb.reverse().toString();
    }

    public static  void  main(String[] args){

        ShortestCommonSupersequence testee = new ShortestCommonSupersequence();

        System.out.println(testee.shortestCommonSupersequence("bbba","bbab"));
    }
}
