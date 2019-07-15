package source.search;


import java.util.*;
import java.util.stream.Collectors;

/*

301. Remove Invalid Parentheses

Favorite

Share
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]
 */


//take away
//
//TODO convert list<character> to string

//str.remove(idx). can not use remove on the arraylist



//TODO convert char[] to characger[]

//str = s.chars().mapToObj(c -> (char) c).toArray(Character[]::new);


//TODO addArrayToList
//Collections.addAll(List<Character>, str);

public class RemoveInvalidParentheses {

    List<Character> str = new LinkedList<>();



    public List<String> removeInvalidParentheses(String s) {

        int l = 0;

        int r = 0;

        Character[] str = s.chars().mapToObj(c -> (char) c).toArray(Character[]::new);

        for(char ch:str){

            if(ch=='(') l++;

                //TODO check first
            else if(l==0 && ch==')') r++;

            else if(ch==')') l--;


        }


        // System.out.println("l,r" + l + "," + r);

        Set<String> set = new HashSet<String>();

        //TODO does not support remove for arrayList List<Character> charList = Arrays.asList(str);
        List<Character> charList = new LinkedList<Character>();

        Collections.addAll(charList, str);

        dfs(charList, 0 , l, r, set);


        List<String> result = new LinkedList();

        result.addAll(set);
        return result;


    }


    public void dfs(List<Character> str, int idx,  int l, int r,  Set<String> result) {


        if(l==0 && r==0 ) {
            if(isValid(str))
                result.add(str.stream().map(e -> e.toString()).collect(Collectors.joining()));
            return;
        }

        for(int s = idx; s < str.size(); s++){

            boolean remove = false;

            char ch = str.get(s);

            if(r>0 && ch == ')') {
                if(s!=0 && str.get(s-1)  == ')') continue;
                str.remove(s);
                dfs(str, s, l, r-1, result);

                remove = true;
                //str.add(idx, ")");

            }
            else if(r== 0 && l>0 && ch =='('){

                if(s!=0 && str.get(s-1)  == '(') continue;

                str.remove(s);
                dfs(str, s, l-1, r, result);
                remove=true;
                //str.add(idx, ")");
            }

            if(remove){
                str.add(s, ch);
            }

        }

    }

    public boolean isValid(List<Character> str){

        int count = 0;

        for(char ch: str){

            if(ch=='('){
                count++;
            }

            else if(ch==')'){
                count --;
                if(count<0) return false;
            }

        }


        return count == 0;


    }


    public static void main(String[] args){

        RemoveInvalidParentheses testee = new RemoveInvalidParentheses();

       // testee.removeInvalidParentheses("()())()").forEach(s -> System.out.println(s));

       // System.out.println(testee.removeInvalidParentheses(")(f").size());


        testee.removeInvalidParentheses("))d)").forEach(s -> System.out.println(s));


        testee.removeInvalidParentheses(")(f").forEach(s -> System.out.println(s));

    }

}
