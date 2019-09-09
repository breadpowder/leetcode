package source.stack;

import java.util.LinkedList;
import java.util.List;


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
        //char[] s =  S.toCharArray();
        int[] a = new int[]{1,2};

        List<StringInStack> lss = new LinkedList<StringInStack>();

        

        lss.add(0, new StringInStack("",1, 1));

        int start = 0;
        for(int i=0; i< S.length(); i++){

            char ch = S.charAt(i);
            if( ch - '0' >=0 && ch - '9' <=0){


                String s = S.substring(start, i+1);


                StringInStack old = lss.get(0) ;

                StringInStack ss = new StringInStack(s, (old.idx * old.number + s.length())*(ch - '0'), ch - '0');

                start = i+1;
            }

            else{


            }

        }
        return "";
    }
}
