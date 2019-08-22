package source.reservoir.samping;

/*398
Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);


 */

import java.util.Random;

//Resvpir sampling, i.e. random choose k from n number streaming:
// 1. initiate k with the first k samples
// 2. for streaming index i  in [k+1, n], generate a random number from [0 to i), if the random number smaller than k, replace with the k sample
public class RandomPickIndex {

    int[] nums;

    Random rand = new Random();;

    public RandomPickIndex(int[] _nums) {
        this.nums = _nums;
    }

    public int pick(int target) {

        int count = 0;

        int result = -1;
        for (int idx = 0; idx < nums.length; idx++) {
            if (nums[idx] == target) {
                count++;

                if (rand.nextInt(count) == 0) result = idx;
            }
        }

        return result;
    }
}
