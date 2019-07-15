package source.dp.knapsnack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;


//please do it again since the speed is only 5%,

//issues:
// computeIfPresent, use a final value outside the clousure
// toInitialMap use Integer.valueOf(0)
public class DeleteAndEarn {

    public int deleteAndEarn(int[] nums) {
        Arrays.sort(nums);


        Map<Integer, Integer> mapSum = new LinkedHashMap<Integer,Integer>();

        //TODO dp[i][1] a value is picked, dp[i][0] i is not picked

        for(int i=0; i< nums.length; i++){

            Integer key = Integer.valueOf(nums[i]);
            if(mapSum.containsKey(key)){
                mapSum.put(key,mapSum.get(key) + key);
            }

            else{
                mapSum.put(key,0);
            }
  /*          mapSum.computeIfAbsent(Integer.valueOf(nums[i]), k-> Integer.valueOf(0));

            //TODO final outside the closure
            final int j = nums[i];
            mapSum.computeIfPresent(Integer.valueOf(j), (k,v) -> {
                return v+j;
            });*/

        }

        int dp[][] = new int[mapSum.size()+1][2];

        dp[0][0] = 0;
        dp[0][1] = 0;

        int i = 1 ;
        Integer lastKey = -1;
        for(Integer key: mapSum.keySet()){

            int max_value = Math.max(dp[i-1][1],dp[i-1][0]);
            dp[i][0] = max_value;

            dp[i][1] = key == lastKey+1 ? dp[i-1][0] + mapSum.get(key): max_value + mapSum.get(key);

            lastKey =key;
            i++;
        }



        return Math.max(dp[i-1][0],dp[i-1][1]);
    }



    public static void main(String[] args){

        DeleteAndEarn testee = new DeleteAndEarn();

        //System.out.println(testee.deleteAndEarn(new int[]{3,1}));

        //System.out.println(testee.deleteAndEarn(new int[]{3,4,2}));

        System.out.println(testee.deleteAndEarn(new int[]{10,8,4,2,1,3,4,8,2,9,10,4,8,5,9,1,5,1,6,8,1,1,6,7,8,9,1,7,6,8,4,5,4,1,5,9,8,6,10,6,4,3,8,4,10,8,8,10,6,4,4,4,9,6,9,10,7,1,5,3,4,4,8,1,1,2,1,4,1,1,4,9,4,7,1,5,1,10,3,5,10,3,10,2,1,10,4,1,1,4,1,2,10,9,7,10,1,2,7,5}));
    }
}
