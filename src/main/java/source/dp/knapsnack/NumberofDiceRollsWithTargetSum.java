package source.dp.knapsnack;

public class NumberofDiceRollsWithTargetSum {

    public int numRollsToTarget(int d, int f, int target) {

        //dp[i+1][target] =
        // sum(dp[i][target-k]) for k in 1 to f

        int dp[] = new int[target+1];

        int kmod = 1000000007;

        dp[0] = 1;

        for(int i=1; i<=d; i++)
            for(int s = target; s> 0; s--)
            {

                int sum =0;

                for(int k=f; k>=1; k--) {

                    if(s-k>=0){
                        sum = (sum + dp[s-k]) % kmod;
                    }
                }

                dp[s] = sum;

            }

        return (int)dp[target];
    }

    public static void main(String[] args) {
        NumberofDiceRollsWithTargetSum testee = new NumberofDiceRollsWithTargetSum();

       // System.out.println(testee.numRollsToTarget(30,30,500));

        System.out.println(testee.numRollsToTarget(2,12,8));

    }
}
