package source.divideAndConquer.binarySearch;

// three methods, this one is divide and conquer
// made an error in stop condition, see below, if low>high is wrong here should be for binary search
public class MaximumSubArray {

    public int maxSubArray(int[] nums) {

        return maxSubDC(0, nums.length-1, nums);

    }


    int maxSubDC(int low, int high, int[] nums) {



        if(low==high) return nums[low];

        //if(low>high) return Integer.MIN_VALUE;

        int mid = (low + high)/2;

        int left = maxSubDC(low, mid, nums);

        int right = maxSubDC(mid+1, high, nums);

        int tempMax = Math.max(left, right);

        if(low > mid || mid+1 > high) return tempMax;

        int cross = maxSubArrayDC(low, high, mid, nums);

        return Math.max(cross, tempMax);



    }


    int maxSubArrayDC(int low, int high, int mid, int[] nums){

        int left_max = nums[mid];
        int left_sum = left_max;

        for(int i=mid-1; i>=low; i--){
            left_sum += nums[i];
            left_max = Math.max(left_max, left_sum);
        }

        int right_max = nums[mid+1];
        int right_sum = right_max;

        for(int i= mid+2; i<=high;i++){
            right_sum += nums[i];
            right_max = Math.max(right_max, right_sum);
        }

        return left_max + right_max;

    }
}
