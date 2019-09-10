package source.stack;

/*
394. Decode String

        Share
        Given an encoded string, return its decoded string.

        The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

        You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

        Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

        Examples:

        s = "3[a]2[bc]", return "aaabcbc".
        s = "3[a2[c]]", return "accaccacc".
        s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

*/

//review 394

// 2[a..d2[a...z]a...z2[a...z]]

// each stack level corresponds to one level of [],after resolve a stack level, we can always combine with previous level

import java.util.LinkedList;

public class DecodeString {
    public String decodeString(String s) {

        LinkedList<StringBuffer> strs = new LinkedList<StringBuffer>();

        strs.add(0, new StringBuffer(""));
        LinkedList<Integer> nums = new LinkedList<Integer>();

        char[] chs = s.toCharArray();

        //StringBuffer result = new StringBuffer();

        StringBuffer tmp = null;
        //int count = 0;

        //...3[a2[c]df]..
        for(int i=0; i <chs.length; i++){


            char ch = chs[i];
            if(ch=='[') {
                // count ++ ;
                tmp = new StringBuffer();
                strs.add(0, tmp);
            }

            else if(ch==']'){
                //count--;

                StringBuffer str = strs.removeFirst();
                StringBuffer sb = new StringBuffer();


                int num = nums.removeFirst();
                for(int j=0; j< num; j++){
                    sb.append(str);
                }
                //if(count==0) {
                //    result.append(sb.toString());
                //}
                //else{
                if(!strs.isEmpty()) sb = strs.removeFirst().append(sb);
                strs.add(0, sb);
                //}
            }


            else if(ch - '0' >= 0 && ch - '0' <=9){
                int number  = ch - '0';
                while(i+1 < chs.length && chs[i+1] - '0' >=0 && chs[i+1] - '0' <=9) {
                    number = number * 10 + chs[i+1] - '0';
                    i++;
                }
                nums.add(0, number);
            }


            //else if(count==0){
            //    result.append(chs[i]);
            // }


            else {
                strs.get(0).append(chs[i]);
            }


        }

        return strs.removeFirst().toString();


    }

    public static void main(String[] args){
        DecodeString ds = new DecodeString();

        //System.out.println(ds.decodeString("3[a]"));
       // System.out.println(ds.decodeString("3[a2[c]]"));
        //2[2[y]pq4[2[jk]e1[f]]]
        System.out.println(ds.decodeString("4[1[j]e1[f]]"));
    }

}
