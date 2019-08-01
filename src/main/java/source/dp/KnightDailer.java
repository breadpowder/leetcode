package source.dp;
/*
935. Knight Dialer
Medium

229

67

Favorite

Share
A chess knight can move as indicated in the chess diagram below:

 .



This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.  Each hop must be from one key to another numbered key.

Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.

How many distinct numbers can you dial in this manner?

Since the answer may be large, output the answer modulo 10^9 + 7.
 */

//take away, here list dp[0-9] you can use [-2,1], [1,2] .... and loop through each position as well

//see https://www.youtube.com/watch?v=HTnIFivp0aw&list=PLLuMmzMTgVK7vEbeHBDD42pqqG36jhuOr&index=14
public class KnightDailer {

    public int knightDialer(int N) {

        int module = 1000000007;

        //dp[i][N]

        long[] dp = new long[10];

        for(int i=0; i<10; i++)
            dp[i]=1;

        for(int n=2; n<=N; n++){

            long[] dp_n = new long[10];
            for(int i=0; i<10; i++){

                if(i==0) dp_n[0] = (dp[4] + dp[6] + module)%module;

                else if(i==1) dp_n[1] = (dp[6] + dp[8]+ module)%module;
                else if(i==2) dp_n[2] = (dp[7] + dp[9]+ module)%module;
                else if(i==3) dp_n[3] = (dp[4] + dp[8]+ module)%module;
                else if(i==4) dp_n[4] = (dp[0] + dp[3] + dp[9]+ module)%module;
                else if(i==5) dp_n[5] = 0;

                else if(i==6) dp_n[6] = (dp[0] + dp[1] + dp[7]+ module)%module;

                else if(i==7) dp_n[7] = (dp[2] + dp[6]+ module)%module;

                else if(i==8) dp_n[8] = (dp[1] + dp[3]+ module)%module;
                else  dp_n[9] = (dp[2] + dp[4]+ module)%module;



            }
            dp = dp_n;
        }

        long sum =0;
        for(int i=0; i<10; i++){
            sum = (sum + dp[i]) % module;

        }

        return (int)sum;
    }
}
