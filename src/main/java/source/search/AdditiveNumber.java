package source.search;

/*
306306. Additive Number


Favorite

Share
Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

Example 1:

Input: "112358"
Output: true
Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
             1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
Example 2:

Input: "199100199"
Output: true
Explanation: The additive sequence is: 1, 99, 100, 199.
             1 + 99 = 100, 99 + 100 = 199
Follow up:
How would you handle overflow for very large input integers?



 */

//take away, see pretty clean use n_2, n_1

public class AdditiveNumber {

    int max = Integer.MAX_VALUE;

    public boolean isAdditiveNumber(String num) {

        return rec(num, 0, 0, 0, 0);

    }


    public boolean rec(String num, int start, int count, long n_2, long n_1){


        if(start >=  num.length() && count >2) return true;

        if(start >= num.length() ) return false;

        long split = 0;

        for(int i=start; i<num.length(); i++)
        {

            if(num.charAt(start) == '0' && i> start) return false;

            split = split * 10 + num.charAt(i) - '0';

            if(count >=2){

                if(split > n_2 + n_1) return false;

                else if(split < n_2 + n_1) continue;

            }

            if(rec(num, i+1, count+1,n_1,split)) return true;

            if(split>max) return false;


        }


        return false;


    }
}
