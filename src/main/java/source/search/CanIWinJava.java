package source.search;

/*
464
In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.

What if we change the game so that players cannot re-use integers?

For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.

Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.

You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.

Example

Input:
maxChoosableInteger = 10
desiredTotal = 11

Output:
false

Explanation:
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
Same with other integers chosen by the first player, the second player will always win.
 */

//take away:

// for status that marker which number are used, i.e. number<20, can use a integer (32 bit) and one an integer array with idx to marker

public class CanIWinJava {

    public boolean canIWin(int N, int desiredTotal) {

        if(N*(N+1)/2<desiredTotal) return false;

        if(desiredTotal==0) return true;


        int[] marker = new int[1<<N];

        return canIWinRec(desiredTotal, 0 ,N, marker);


    }


    public boolean canIWinRec(int total, int used, int N ,int[] marker){


        if(total<=0) return false;

        if(marker[used]!=0) return marker[used] == 1;


        for(int i=0; i<N; i++)
        {
            if((used & (1<<i)) ==0){

                if(!canIWinRec(total-(i+1), used | (1<<i), N, marker)){

                    marker[used] = 1;

                    return true;

                }


            }

        }


        marker[used] = -1;
        return false;

    }

}
