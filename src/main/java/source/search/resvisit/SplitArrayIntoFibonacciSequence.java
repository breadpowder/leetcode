package source.search.resvisit;

//842
/*
Given a string S of digits, such as S = "123456579", we can split it into a Fibonacci-like sequence [123, 456, 579].

Formally, a Fibonacci-like sequence is a list F of non-negative integers such that:

0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);
F.length >= 3;
and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.
Also, note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the piece is the number 0 itself.

Return any Fibonacci-like sequence split from S, or return [] if it cannot be done.

Example 1:

Input: "123456579"
Output: [123,456,579]
Example 2:

Input: "11235813"
Output: [1,1,2,3,5,8,13]
Example 3:

Input: "112358130"
Output: []
Explanation: The task is impossible.
Example 4:

Input: "0123"
Output: []
Explanation: Leading zeroes are not allowed, so "01", "2", "3" is not valid.
Example 5:

Input: "1101111"
Output: [110, 1, 111]
Explanation: The output [11, 0, 11, 11] would also be accepted.
Note:

1 <= S.length <= 200
S contains only digits.
 */

/*
Take away:
Optimize:
1. in each loop, find split points by using num= num * 10 + ch - '0' instead of using Integer.valueOf(s.substring(s, en)),


2. when deciding to add a split, use continue clause , and
 */

import java.util.ArrayList;
import java.util.List;

public class SplitArrayIntoFibonacciSequence {


    int max = Integer.MAX_VALUE/10;

    public List<Integer> splitIntoFibonacci(String S) {

        List<Integer> result = new ArrayList<Integer>();
        rec(S, 0, result);
        return result;

    }


    public boolean rec(String s, int start, List<Integer> splits){

        if(start>=s.length() && splits.size()>=3) return true;

        if(start>=s.length()) return false;

        int split = 0 ;

        for(int i= start; i< s.length(); i++){

            if(s.charAt(start) =='0' && i>start) return false;

            split = split * 10 + (s.charAt(i)- '0');

            // int split =  Integer.valueOf(s.substring(start, i+1));

            int n = splits.size();

            if(n>=2){

                if(split>splits.get(n-2) + splits.get(n-1)){
                    return false;
                }

                if(split<splits.get(n-2) + splits.get(n-1)){

                    //if(split>max) return false;
                    //else
                    continue;
                }

            }

            splits.add(split);

            if(rec(s, i+1, splits)) return true;

            splits.remove(splits.size()-1);

            if(split>max) return false;



 /*
 Code duplicates, only add split if satify f(n) = f(n-1) + f(n-2)
            if(n>=2){



                if(splits.get(n-2) + splits.get(n-1) == split){

                    splits.add(split);

                    if(rec(s, i+1, splits)) return true;

                    splits.remove(splits.size()-1);

                }
            }



            else{
               // System.out.println("Adding split " + split);
                 splits.add(split);
                 if(rec(s, i+1, splits)) return true;

                 // System.out.println("Removing splits " + splits.get(splits.size()-1));

                 splits.remove(splits.size()-1);


            }*/

        }

        return false;

        //split and check


    }
}
