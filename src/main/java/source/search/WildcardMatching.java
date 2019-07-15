package source.search;

/*
44
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false

 */

//Take away: did not figure out memorization at the first place, because did not figure out time complexuty and was thinking too hard of use NFA regular expression
public class WildcardMatching {

    public boolean isMatch(String s, String pattern) {

        StringBuilder sb = new StringBuilder();

        for(int idx=0; idx<pattern.length(); idx++){

            if(pattern.charAt(idx)=='*' && idx<pattern.length()-1 && pattern.charAt(idx+1) == '*')
            {
                continue;
            }

            sb.append(pattern.charAt(idx));
        }

        char[] p = sb.toString().toCharArray();

        int totalChar =0;

        for(char ch: p){

            if(ch>='a') totalChar++;


        }

        int isMatched[][] = new int[s.length()][p.length];

        return match(p, 0, s, 0, totalChar,isMatched);


    }



    public boolean match(char[] p, int p_idx, String s, int s_idx, int remain, int[][] isMatched){

        System.out.println(p_idx + "|" + s_idx);

        if(remain>s.length()-s_idx){

            return false;
        }


        if(s_idx==s.length() && p_idx==p.length)
            return true;

        if(s_idx==s.length() && p_idx < p.length)
        {
            for(int i= p_idx; i<p.length; i++)
            {
                if(p[i]!='*') return false;
            }

            return true;
        }

        if(s_idx==s.length() || p_idx==p.length)
            return false;


        if(isMatched[s_idx][p_idx]!=0) return isMatched[s_idx][p_idx] == 1;

        boolean matched = false;

        if(p[p_idx] == '?') {
            matched = match(p, p_idx+1, s, s_idx+1, remain,isMatched);
        }

        else if(p[p_idx] == '*')
        {
            matched =  match(p, p_idx+1,s,s_idx+1, remain,isMatched) || match(p, p_idx+1, s, s_idx,remain,isMatched) || match(p, p_idx,s,s_idx+1, remain,isMatched) ;
        }

        else if(p[p_idx] == s.charAt(s_idx)){

            matched = match (p, p_idx+1, s, s_idx+1, remain-1,isMatched);
        }


        isMatched[s_idx][p_idx] = matched? 1 : -1;
        return matched;


    }

    public static void main(String[] args){
        WildcardMatching wm = new WildcardMatching();

       String s= "abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb";
        String pattern = "*aa*a*aba*ac*ab***";// ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb";

       long start = System.currentTimeMillis();
        System.out.println(wm.isMatch(s,pattern));
        System.out.println("Took " + (System.currentTimeMillis() - start)/1000 + " second");

    }
}



