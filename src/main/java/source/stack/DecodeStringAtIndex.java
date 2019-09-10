package source.stack;

import java.util.LinkedList;
import java.util.List;
/*
880

An encoded string S is given.  To find and write the decoded string to a tape,
the encoded string is read one character at a time and the following steps are taken:

If the character read is a letter, that letter is written onto the tape.
If the character read is a digit (say d), the entire current tape is repeatedly written d-1 more times in total.
Now for some encoded string S, and an index K, find and return the K-th letter (1 indexed) in the decoded string.

Example 1:

Input: S = "leet2code3", K = 10
Output: "o"
Explanation:
The decoded string is "leetleetcodeleetleetcodeleetleetcode".
The 10th letter in the string is "o".
Example 2:

Input: S = "ha22", K = 5
Output: "h"
Explanation:
The decoded string is "hahahaha".  The 5th letter is "h".
Example 3:

Input: S = "a2345678999999999999999", K = 1
Output: "a"
Explanation:
The decoded string is "a" repeated 8301530446056247680 times.  The 1st letter is "a".


Note:

2 <= S.length <= 100
S will only contain lowercase letters and digits 2 through 9.
S starts with a letter.
1 <= K <= 10^9
The decoded string is guaranteed to have less than 2^63 letters.

use prev for previous letter, cur for current number of letters, and total cur * num

if k = 10, k
if k= 14, <
 */



//
public class DecodeStringAtIndex {

    public class StringInStack{

        public String s;

        public int idx;

        public int number;


        StringInStack(String s, int idx, int number){


            this.s= s;
            this.number = number;
            this.idx = idx;
        }

    }

    public String decodeAtIndex(String S, int K) {

        return decodeAtIndex(S, (long)K);

    }


    String decodeAtIndex(String S, long K){


        lss.add(0, new StringInStack("",1, 1));

        //sbs.add(new StringBuilder(""));

        long count =0l;

        for(int i=0; i< S.length(); i++){

            char ch = S.charAt(i);
            if( ch - '0' >=0 && ch - '9' <=0){

                String s = S.substring(start, i+1);

                int num = ch - '0';
                //StringBuilder sb = sbs.get(0);


                if(K > count*num){
                    count = count * num;
                    continue;
                }

                else if(K%count==0 ){
                    return decodeAtIndex(S.substring(0, i), count);
                }


                else{
                    return decodeAtIndex(S.substring(0,i), K%count);
                }

                //  count = new_count;

                //  sbs.add(0, new StringBuilder(""));



            }


            else{

                //StringBuilder sb = sbs.get(0);
                //sb.append(S.substring(i,i+1));
                count++;
                if(count==K) return S.substring(i, i+1);

            }

        }

        return "";

    }
}
