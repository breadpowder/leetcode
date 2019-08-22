package source.tree.TreeMap;

import java.util.TreeMap;
import java.util.Map;

/*/
327
Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:

Input: nums = [-2,5,-1], lower = -2, upper = 2,
Output: 3
Explanation: The three ranges are : [0,0], [2,2], [0,2] and their respective sums are: -2, -1, 2.

 */
public class CountOfRangeSum {

    public int countRangeSum(int[] nums, int lower, int upper) {

        //tm put prefix sum, if lower < prefix_sum[i] - prefix_sum[j] <upper
        TreeMap<Long, Long> tm = new TreeMap<>();

        tm.put(0l, 1l);

        long sum = 0l;

        int result =0;
        for(int n: nums){

            sum += n;
            long from = sum - upper;
            long to = sum -  lower;

            Map<Long, Long> subMap = tm.subMap(from, true, to, true);


            // System.out.println(size);
            result += getSum(subMap.values());

            if(tm.containsKey(sum))
            {
                tm.put(sum, tm.get(sum)+1);
            }
            else{
                tm.put(sum, 1l);
            }
        }

        return result;
    }

    private long getSum(java.util.Collection<Long> values){
        long sum =0;

        for(long v: values){
            sum +=v;
        }

        return sum;

    }
}
