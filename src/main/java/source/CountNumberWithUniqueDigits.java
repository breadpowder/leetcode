package source;

import java.util.ArrayList;
import java.util.List;

/*
357. Count Numbers with Unique Digits
Medium

235

614

Favorite

Share
Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

Example:

Input: 2
Output: 91
Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,
             excluding 11,22,33,44,55,66,77,88,99
 */

//take away:

//1. integer can not pass by reference, i.e. modify(integer v){ v = somevalue); won't retunr back, so have to use an array
//2. don't call function (parmater++), this change the local variable, should use parameter -1 instead
//3 . in a function arr[i]++ works

public class CountNumberWithUniqueDigits {

    List<Integer> result = new ArrayList<Integer>();

    public int countNumbersWithUniqueDigits(int n) {

        int[] count = new int[1];

        for(int i=0; i<=n; i++)
        {
            countNumber(i,0, count,1,0);
        }


        return count[0];
    }


    public void countNumber(int digit, int marker, int[] count, int start, int num){

        if(digit == 0){
            result.add(num);

            count[0]++;
            return;
        }

        for(int i=start; i<=9; i++)
        {
            if((marker&(1<<i))==0){
                countNumber(digit-1, marker | (1<<i), count,0, num*10 + i);
            }
        }

    }


    public static void main(String[] args){

        CountNumberWithUniqueDigits testee = new CountNumberWithUniqueDigits();

        System.out.println(testee.countNumbersWithUniqueDigits(2));

        testee.result.forEach(v -> System.out.println(v));

    }
}
