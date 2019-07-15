package source.dp;

/*
845. Longest Mountain in Array
Medium

Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:

B.length >= 3
There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
(Note that B could be any subarray of A, including the entire array A.)

Given an array A of integers, return the length of the longest mountain.

Return 0 if there is no mountain.

Example 1:

Input: [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
Example 2:

Input: [2,2,2]
Output: 0
Explanation: There is no mountain.
Note:

0 <= A.length <= 10000
0 <= A[i] <= 10000
Follow up:

Can you solve it using only one pass?
Can you solve it in O(1) space?
 */

//take away
// simular as 926, we have two subproblems in array, i.e. two directions
// Math.max(increaseArr[i] + decreaseArr[i] +1
public class LongestMountainInArray {

    public int longestMountain(int[] A) {

        int[] increaseArr = new int[A.length];

        int[] decreaseArr = new int[A.length];

        int result = 0;

        for(int i=1; i<A.length; i++){

            increaseArr[i] = (A[i] > A[i-1]) ? increaseArr[i-1]+1: 0;
        }

        for(int i= A.length-2; i>=0; i--){
            decreaseArr[i] = (A[i] < A[i+1]) ? decreaseArr[i+1]+1:0;
        }

        for(int i=1; i<= A.length-2; i++){
            if(increaseArr[i]>0 && decreaseArr[i]>0) {
                result = Math.max(increaseArr[i] + decreaseArr[i] +1 , result);
            }
        }
        return result;
    }


}
