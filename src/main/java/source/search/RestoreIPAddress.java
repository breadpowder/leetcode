package source.search;

import java.util.LinkedList;
import java.util.List;


/*
93
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
 */
public class RestoreIPAddress {

    public List<String> restoreIpAddresses(String s) {


        List<String> result = new LinkedList<String>();

        //TODO use this to prevent long string

        if(s.length()>12) return result;


        rec(s,0, 0, new int[4], result);

        return result;

    }

    public void rec(String s, int idx, int count, int[] splits, List<String> result){

        //TODO should be 4 not 5, reason is ...

        //TODO use splits and skip string evaluation in each node

        if(count == 4 && idx == s.length()){

            String[] str = {s.substring(0, splits[0]+1),
                    s.substring(splits[0]+1, splits[1]+1),
                    s.substring(splits[1]+1, splits[2]+1),
                    s.substring(splits[2]+1, s.length())};
            result.add(String.join(".", str));
            //result.add(str.stream().map(e -> e.toString()).collect(Collectors.joining()));
            return;
        }

        if(count == 4){
            return;
        }

        int number  = 0 ;
        for(int start =idx; start <= Math.min(s.length()-1, idx+2); start ++)
        {

            number = number *10  + s.charAt(start) - '0';

            if(number>=256){

                break;
            }


            else{

                //TODO no splits backtracking
                splits[count] = start;

                rec(s, start + 1, count+1, splits, result);

                if(number == 0)
                    break;

            }

        }
    }
}
