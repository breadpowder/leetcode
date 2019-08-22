package source.divideAndConquer.binarySearch;

/*

69
Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2

Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since
             the decimal part is truncated, 2 is returned.

 */

//Note: 1. remember [), so the parameter needs to be end+1
// Can be overflowed, so the end must be sqrt(Integer.MAX_VALUE)

public class Sqrt {

    public int mySqrt(int x) {

        int end = (int)Math.sqrt(Integer.MAX_VALUE);

        //overflow exception due to (x+1)^2 > Math
        int n = sqrt(0, end+1, x);

        return n-1;

    }

    //[)
    public int sqrt(int l, int r, int x){


        while(l<r){

            int m = (l+r)/2;

            if(m*m>x){
                r = m;
            }

            else{
                l = m+1;
            }
        }

        return l;

    }


}
