package source.twopointers;

/*
986
Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)



Example 1:

Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.



Note:

    0 <= A.length < 1000
    0 <= B.length < 1000
    0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9

 */

import java.util.LinkedList;
import java.util.List;

//Take away: list can convert to array: list.toArray(new int[][]), array type,
// remeber, both first and last are sorted in A and B
public class IntervalListIntersections {

    public int[][] intervalIntersection(int[][] A, int[][] B) {

        //suppose dis [i]

        int a_len = A.length;
        int b_len = B.length;

        List<int[]> result = new LinkedList<>();

        int a_idx = 0;

        int b_idx = 0;

        while(a_idx< a_len && b_idx <b_len){

            int a_first = A[a_idx][0];
            int a_last = A[a_idx][1];

            int b_first = B[b_idx][0];
            int b_last = B[b_idx][1];


            //intersection and next
            if(a_last<=b_last){
                if(a_last>=b_first) {
                    int first = b_first> a_first ? b_first : a_first;
                    result.add(new int[]{first, a_last});
                }
                a_idx++;
            }

            else{
                if(b_last>=a_first) {
                    int first = b_first> a_first ? b_first : a_first;
                    result.add(new int[]{first,b_last});
                }
                b_idx++;
            }

        }


        return result.toArray(new int[0][0]);

    }
}
