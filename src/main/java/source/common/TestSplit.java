package source.common;

import java.net.URLDecoder;

public class TestSplit {


    public static void main(String args[]) throws Exception{

        //String str = "proxy-user=usrda@0datapassport.com";


        //System.out.println(URLDecoder.decode(str,"UTF-8"));

        //StringBuilder sb = new StringBuilder();


        String[] path = "/a//b/".split("/");
        for(String p : path)
        System.out.println("next" + p);
    }
}
