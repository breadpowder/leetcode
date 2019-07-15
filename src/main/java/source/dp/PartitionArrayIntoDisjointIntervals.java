package source.dp;
/*
915. Partition Array into Disjoint Intervals
Medium


Share
Given an array A, partition it into two (contiguous) subarrays left and right so that:

Every element in left is less than or equal to every element in right.
left and right are non-empty.
left has the smallest possible size.
Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.



Example 1:

Input: [5,0,3,8,6]
Output: 3
Explanation: left = [5,0,3], right = [8,6]
Example 2:

Input: [1,1,1,0,6,12]
Output: 4
Explanation: left = [1,1,1,0], right = [6,12]


Note:

2 <= A.length <= 30000
0 <= A[i] <= 10^6
It is guaranteed there is at least one way to partition A as described.
 */


//TODO review not fast enough, only 22.10 and 5.06 read people's code
//see this with const memory and scan once https://leetcode.com/problems/partition-array-into-disjoint-intervals/discuss/294793/Java-faster-than-100-less-memory-than-100

public class PartitionArrayIntoDisjointIntervals {

    public int partitionDisjoint(int[] A) {


        int dp[][] = new int[A.length][2];

        int l_max = A[0];
        int r_min = A[A.length-1];

        for(int i=0; i< A.length; i++){

            if(A[i]>l_max) {

                l_max = A[i];
            }

            dp[i][0] = l_max;

            int j = A.length-1-i;

            if(A[j]<r_min){

                r_min = A[j];
            }

            dp[j][1] = r_min;


        }


        for(int i=0; i<A.length-1; i++){
            if(dp[i][0] <= dp[i+1][1]) return i+1;
        }

        return -1;

    }
}
