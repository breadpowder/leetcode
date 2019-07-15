package source;

import java.lang.reflect.Array;
import java.util.Arrays;


/*60. Permutation Sequence
        Medium

        826

        223

        Favorite

        Share
        The set [1,2,3,...,n] contains a total of n! unique permutations.

        By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

        "123"
        "132"
        "213"
        "231"
        "312"
        "321"
        Given n and k, return the kth permutation sequence.

        Note:

        Given n will be between 1 and 9 inclusive.
        Given k will be between 1 and n! inclusive.
        Example 1:

        Input: n = 3, k = 3
        Output: "213"
        Example 2:

        Input: n = 4, k = 9
        Output: "2314"*/

public class PermutationSequence {

    public String getPermutation(int n, int k) {

        boolean[] marker = new boolean[n + 1];

        int[] factorial = new int[n + 1];

        factorial[0] = 1;

        for (int i = 1; i <= n; i++) {

            factorial[i] = i * factorial[i - 1];
        }


        char[] result = new char[n];


        for (int i = n; i >= 1; i--) {
            int number;

            if (k > factorial[i - 1]) {
                int rank = k / factorial[i - 1] + ((k % factorial[i-1]) == 0 ? 0: 1);
                number = getRank(rank, marker);
                k = k - (rank -1) * factorial[i-1];
            } else {
                number = getRank(1, marker);
            }

            marker[number] = true;
            result[n - i] = (char) (number + '0');
        }
        return new String(result);
    }


    public int getRank(int rank, boolean[] marker){

        int index = 0;

        for(int idx =1,cur = 0; cur<rank; idx++)
        {
            if(!marker[idx]){
                cur ++;
            }

            index = idx;
        }
        return index;
    }

    public static void main(String[] args) {

        PermutationSequence seq = new PermutationSequence();

        System.out.println(seq.getPermutation(2, 2));


        System.out.println(seq.getPermutation(4, 1));


        System.out.println(seq.getPermutation(3, 3));


        System.out.println(seq.getPermutation(4, 9));
    }


}
