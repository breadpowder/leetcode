package source.tree.fenwick;

import java.util.Arrays;
import java.util.Comparator;

/*
493. Reverse Pairs
Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2

Example2:

Input: [2,4,3,5,1]
Output: 3

Note:

    The length of the given array will not exceed 50,000.
    All the numbers in the input array are in the range of 32-bit integer.

 */
public class ReversePairs {
    public static class BIT {

        private int[] rank;
        public BIT(int size){
            this.rank = new int[size];
        }

        public void update(int idx){

            for(;idx<rank.length; idx += (idx & -idx)){
                rank[idx] += 1;
            }
        }

        public int count(int idx){
            int result = 0;
            for(;idx>0; idx -= (idx & -idx)){
                result +=rank[idx];
            }
            return result;
        }
    }


    public int reversePairs(int[] nums) {

        int result =0;

        if(nums.length==0) return result;

        long[][] numsWithPos = new long[nums.length][2];

        for(int i=0; i< nums.length; i++){
            numsWithPos[i][0] = nums[i];
            numsWithPos[i][1] = i;
        }
        // use lambda expression is slow
        //Arrays.sort(numsWithPos,(a,b) -> a[0] - b[0]);

        Comparator<long[]> cmp = new Comparator<long[]>() {

            public int compare(long[] o1, long[] o2) {
                return Long.valueOf(o1[0]).compareTo(Long.valueOf(o2[0]));
            }
        };


        Arrays.sort(numsWithPos, cmp);

        int[] rank = new int[nums.length];

        int id=0;
        rank[(int)numsWithPos[0][1]] = ++id;

        for(int i=1;  i< nums.length; i++){
            if(numsWithPos[i][0] == numsWithPos[i-1][0]){
                rank[(int)numsWithPos[i][1]] = id;
            }

            else{
                rank[(int)numsWithPos[i][1]] = ++id;
            }
        }


        BIT bits = new BIT(id+1);

        for(int i= 0 ; i<nums.length; i++){

            // TODO out of boundary

           long upper = ((long)nums[i])*2 + 1;

            //TODO find the first idx that equals and larger than upper
            //must be sortedc
            int idx = binarySearch(numsWithPos, upper, 0, nums.length-1);

            int subCount = (idx == nums.length) ? 0 : i - bits.count(rank[(int)numsWithPos[idx][1]]-1);

            result += subCount;

            bits.update(rank[i]);


        }

        return result;

    }



    int binarySearch(long[][] nums, long target, int l, int h){

        while(l<=h){

            int mid_idx = (l+h)/2;
            long mid = nums[mid_idx][0];

            if(target<=mid) {
                h = mid_idx-1;
            }

            else  l= mid_idx + 1;
        }

        return l;

    }

    public static void main(String[] args) {
        ReversePairs testee = new ReversePairs();

        int[] test0 = new int[]{1,3,2,3,1};
       // int result0 = testee.reversePairs(test0);

        int[] test1 = new int[]{5,4,3,2,1};
        int result1 = testee.reversePairs(test1);

        int[] test3 = new int[]{2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647};
        int result3 = testee.reversePairs(test3);
        System.out.println(result3);

        int[] test4 = new int[]{2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647};
        int result4 = testee.reversePairs(test4);
        System.out.println(result4);
    }
}


