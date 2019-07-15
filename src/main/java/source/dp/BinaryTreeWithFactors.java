package source.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BinaryTreeWithFactors {


    int kMod = 1000000007;
    public int numFactoredBinaryTrees(int[] A) {

        Arrays.sort(A);


        long dp[] = new long[A.length];

        Map<Integer, Integer> s = new HashMap<Integer,Integer>();

        for(int i=0; i<A.length; i++){
            s.put(A[i],i);
            dp[i] =1;
        }


        for(int i=1; i<A.length; i++){
            for(int j=0; j<i; j++){

                if(A[i]%A[j]==0){

                    int k = A[i] /A[j];

                    if(s.containsKey(k)){
                        int idx = s.get(Integer.valueOf(k));

                        //TODO trick here a * b = c, will encounter a and b each once,
                        dp[i] =  (dp[i] + dp[j] * dp[idx] + kMod) % kMod;
                    }
                }
            }

        }



        long result  =0;

        for(int i=0; i<A.length; i++){

            result = (result + dp[i] + kMod)%kMod;
        }

        return (int)result;

    }


    public static void main(String[] args){
        BinaryTreeWithFactors testee = new BinaryTreeWithFactors();

        System.out.println(testee.numFactoredBinaryTrees(new int[]{15,13,22,7,11}));
    }
}
