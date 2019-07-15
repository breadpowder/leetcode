package source;


import java.util.Arrays;
import java.util.stream.Stream;

/*
738. Monotone Increasing Digits
Medium

239

42

Favorite

Share
Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.

(Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)

Example 1:
Input: N = 10
Output: 9
Example 2:
Input: N = 1234
Output: 1234
Example 3:
Input: N = 332
Output: 299
 */

//take away, copy array Arrays.copyOf(arr, length)
public class MonotoneIncreasingDigits {

    public int monotoneIncreasingDigits(int N) {

        char[] s = String.valueOf(N).toCharArray();


        int digit = isIncreasing(Arrays.copyOf(s, s.length));

        if(digit==-1){

            return N;
        }

        else{

            for(int i=digit+1; i<s.length; i++)
            {
                s[i] = '9';
            }


            s[digit] -= 1;
        }

        int result = 0;

        for(int i=0; i<s.length; i++){

            result = result*10 + (s[i] - '0');
        }

        return result;

    }


    int isIncreasing(char[] s){

        int digit = -1;

        for(int i=s.length-1; i>0; i--){

            if(s[i-1]>s[i]){

                s[i-1] -= 1;
                digit = i-1;
            }

        }

        return digit;

    }
}

/*
bApproach #1: Greedy [Accepted]
Intuition

Let's construct the answer digit by digit.

If the current answer is say, 123, and the next digit is 5, then the answer must be at least 123555...5, since the digits in the answer must be monotonically increasing. If this is larger than N, then it's impossible.

Algorithm

For each digit of N, let's build the next digit of our answer ans. We'll find the smallest possible digit d such that ans + (d repeating) > N when comparing by string; that means d-1 must have satisfied ans + (d-1 repeating) <= N, and so we'll add d-1 to our answer. If we don't find such a digit, we can add a 9 instead.


Complexity Analysis

Time Complexity: O(D^2)O(D
2
 ), where D \approx \log ND≈logN is the number of digits in NN. We do O(D)O(D) work building and comparing each candidate, and we do this O(D)O(D) times.

Space Complexity: O(D)O(D), the size of the answer and the temporary string we are comparing.
 */
