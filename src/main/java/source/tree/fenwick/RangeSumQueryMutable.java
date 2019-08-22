package source.tree.fenwick;

/*
307
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.

Example:

Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8

Note:

    The array is only modifiable by the update function.
    You may assume the number of calls to update and sumRange function is distributed evenly.


 */
public class RangeSumQueryMutable {
    int[] nums;
    int[] sum;

    public RangeSumQueryMutable(int[] _nums) {

        this.nums = new int[_nums.length];
        this.sum = new int[nums.length+1];


        for(int i=0; i < nums.length; i++){
            update(i, _nums[i]);
        }


    }

    public void update(int i, int val) {

        int delta = val - nums[i];
        nums[i] = val;

        int k = i+1;
        for(; k<sum.length; k+= lastBit(k)){
            sum[k] += delta;
        }


    }

    public int sumRange(int i, int j) {

        return prefix(j) - prefix(i-1);

    }

    private int prefix(int i){
        int k=i+1;

        int result =0;

        for(; k>0; k -= lastBit(k)){

            result += sum[k];
        }

        return result;
    }


    private int lastBit(int i){
        return i & (-i);
    }

}

