package source.dp.subSequence.palindro;

/*
647. Palindromic Substrings


Favorite

Share
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".


Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 */
//TODO ram optimized, since length only depends on the lastest two length dp[i][i+len] depends on dp[i+1][i+len-1]

public class PalindromicSubString {

    public int countSubstrings(String s) {

        //Base case
        //dp[i][j] = true from i to j f dp[i-1][j-1] is true and s[i] = s[j]

        //TODO Base case remeber len ==2 is also a base case since i+1 > j-1
        //TODO optimize on space, use
        char[] str = s.toCharArray();

        boolean dp[][] = new boolean[s.length()][2];


        //TODO can count on the fly


        int count = s.length();

        for(int i=0; i< s.length(); i++){

            dp[i][0] = true;
            dp[i][1] = true;

        }


        for(int len = 2; len <= str.length; len++) {
            for (int i = 0; i <= str.length - len; i++) {

                int j = i + len -1;

                dp[i][len&1] = str[i] == str[j] ? dp[i+1][len&1] : false;
                count += dp[i][len&1] ? 1: 0;
            }
        }



        return count;
    }
}
