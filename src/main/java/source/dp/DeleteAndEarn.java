package source.dp;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

//740
/*
Given an array nums of integers, you can perform operations on the array.

In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.

You start with 0 points. Return the maximum number of points you can earn by applying such operations.

Example 1:

Input: nums = [3, 4, 2]
Output: 6
Explanation:
Delete 4 to earn 4 points, consequently 3 is also deleted.
Then, delete 2 to earn 2 points. 6 total points are earned.


Example 2:

Input: nums = [2, 2, 3, 3, 3, 4]
Output: 9
Explanation:
Delete 3 to earn 3 points, deleting both 2's and the 4.
Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
9 total points are earned.
 */
//please do it again since the speed is only 5%,

//issues:
// computeIfPresent, use a final value outside the clousure
// toInitialMap use Integer.valueOf(0)

//Note:
//
//The length of nums is at most 20000.
//Each element nums[i] is an integer in the range [1,10000]


//TODO see the nums[i] is < 10001 use count sort
// see a[i] only depends a[i-1] can reduce space
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
