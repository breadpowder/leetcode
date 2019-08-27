package source.twopointers;

/*
1004. Max Consecutive Ones III
Medium

Given an array A of 0s and 1s, we may change up to K values from 0 to 1.

Return the length of the longest (contiguous) subarray that contains only 1s.



Example 1:

Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
Output: 6
Explanation:
[1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.

Example 2:

Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
Output: 10
Explanation:
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.


 */
//Thoughts: 0 1 1 0 1, what is the first 0 to change in the current window?  find the first idx pre[++count]=idx and A[idx]=0
public class MaxConsecutiveOnesIII {
    public int longestOnes(int[] A, int K) {

        //count the number of 0 encounered
        int[] pre = new int[A.length+1];

        int count=0;
        int max = 0;
        int result = 0;

        for(int i=0; i< A.length; i++){

            if(A[i]==0) pre[++count] = i;

            int len = count - K <= 0? i+1: i - pre[count-K];

            result = Math.max(len, result);

        }

        return result;

    }
}
