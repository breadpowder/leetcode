package source.dp;

/*

664
There is a strange printer with the following two special requirements:

The printer can only print a sequence of the same character each time.
At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.
Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.

Example 1:
Input: "aaabbb"
Output: 2
Explanation: Print "aaa" first and then print "bbb".
Example 2:
Input: "aba"
Output: 2
Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
 */


//take away l = 1
// why relax a[]
public class StrangePointers {

    public int strangePrinter(String s) {

        if(s==null || s.isEmpty()) return 0;


        //aaacbbbc

        //dp[i][j] = dp[i][k] + dp[k+1][j-1] s[k] == s[j]

        int[][] dp = new int[s.length()][s.length()];

        //initial
        for(int i=0; i<s.length();i++){
            for(int j=0; j<s.length(); j++)
                if(i==j) dp[i][j]=1;
                else dp[i][j] = Integer.MAX_VALUE;
        }

        for(int l=1; l<s.length(); l++)
            for(int i=0; i<s.length()-l; i++){
                int j = i + l;

                //TODO relax
                dp[i][j] = dp[i][j-1]+1;
                for(int k=j-1; k>=i; k--){
                    if(s.charAt(k) == s.charAt(j)){
                        if(k==j-1)
                            dp[i][j] = Math.min(dp[i][j],dp[i][j-1]);

                        else
                            dp[i][j]=Math.min(dp[i][k] + dp[k+1][j-1],dp[i][j]);
                    }
                }

            }


        return dp[0][s.length()-1];

    }

    public static void main(String[] args) {
        StrangePointers sp = new StrangePointers();

        int result = sp.strangePrinter("dddccbdbababaddcbcaabdbdddcccddbbaabddb");

        System.out.println(result);

        result = sp.strangePrinter("bcaabdcbacbbcabacbaababdbaddaa");
        System.out.println(result);


    }
}
