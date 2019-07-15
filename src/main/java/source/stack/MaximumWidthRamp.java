package source.stack;


/*

962. Maximum Width Ramp
Given an array A of integers, a ramp is a tuple (i, j) for which i < j and A[i] <= A[j].  The width of such a ramp is j - i.

Find the maximum width of a ramp in A.  If one doesn't exist, return 0.



Example 1:

Input: [6,0,8,2,1,5]
Output: 4
Explanation:
The maximum width ramp is achieved at (i, j) = (1, 5): A[1] = 0 and A[5] = 5.
Example 2:

Input: [9,8,1,0,1,9,4,0,4,1]
Output: 7
Explanation:
The maximum width ramp is achieved at (i, j) = (2, 9): A[2] = 1 and A[9] = 1.
 */

//take away:

// haven't thought about the data structure and algorithm first time, read it again

//binary search if use [], when to stop ? low> high, or if [) to stop if low>=high

import java.util.Arrays;
import java.util.List;

import java.util.LinkedList;

public class MaximumWidthRamp {

    public int maxWidthRamp(int[] A) {

        // monotonically decresing

        List<Integer> monotonicallyDecreasing = new LinkedList<Integer>();

        int minHead = Integer.MAX_VALUE;

        for(int i=0; i< A.length; i++){


            if(A[i]<minHead) {
                monotonicallyDecreasing.add(0, i);
                minHead = A[i];
            }
        }

        int maxLength = 0;

        Integer[] decreaseArr = monotonicallyDecreasing.toArray(new Integer[0]);

        // binary search find the first one smaller than
       // Arrays.stream(decreaseArr).forEach(x -> System.out.print(x));

        System.out.println();

        for(int j=A.length-1; j>0; j--){

              int idx =  binarySearch(decreaseArr, 0, decreaseArr.length-1, A[j], A);

              System.out.println(A[decreaseArr[idx]] + "," + A[j]);

              if(idx!=decreaseArr.length) {

                  maxLength = Math.max(maxLength, j - decreaseArr[idx]);
              }

        }


       /* for(int j=A.length-1; j>0; j--){


            for(int i: decreaseArr) {

                //TODO i can further decrease, should be continue
                if(j-i<=maxLength) continue;

                else if (A[j] < A[i]) break;

                else {
                    maxLength = Math.max(maxLength, j-i);
                }

            }


        }*/

        return maxLength;
    }

    int binarySearch(Integer[] decreasingArr, int low, int high, int num, int[] A){

        //minus one, c
        if(low > high) return low-1;

        int mid = (low + high) /2 ;

        if(num==A[decreasingArr[mid]]) return mid;

        if(num < A[decreasingArr[mid]]) return binarySearch(decreasingArr, low, mid-1, num, A);

        else return binarySearch(decreasingArr, mid+1, high, num, A);

    }

    public static void main(String[] args){

        MaximumWidthRamp testee = new MaximumWidthRamp();
        int result = testee.maxWidthRamp(new int[]{9,8,1,0,1,9,4,0,4,1});

        System.out.println("result: " + result);
    }
}
