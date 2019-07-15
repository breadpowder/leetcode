package source;

import scala.Char;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AddAndSearchWord {

    Trie trie;
    /** Initialize your data structure here. */
    public AddAndSearchWord() {

        this.trie = new Trie();

    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {

        this.trie.addWord(word);
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {

        return trie.search(word);
    }


    public static class Trie{

        TrieNode root;


        public Trie(){
            root = new TrieNode();
        }

        public void addWord(String w){

            TrieNode p = root;

            for(int i=0; i<w.length(); i++)
            {
                p = p.addChild(w.charAt(i));
            }

        }

        //TODO logic should be here instead of TrieNode
        public boolean search(String w){

            return root.search(w,0);

        }

    }



    public static class TrieNode{


        TrieNode[] child = new TrieNode[26];

        public TrieNode(){

        }


        public TrieNode addChild(char c){

            int idx =  c - 'a';

            if(child[idx]==null) child[idx] = new TrieNode();

            return child[idx];

        }



        public boolean search(String w, int s){




            if(s>=w.length()) return true;

            if(w.charAt(s) == '.') {

                for(TrieNode c: child){
                    if(c!=null){
                        if(c.search(w, s+1)) return true;
                    }

                }

                return false;
            }

            else{
                int idx = w.charAt(s) - 'a';

                if(child[idx]==null) return false;

                return child[idx].search(w, s+1);
            }

        }



    }

    public static void main(String[] args){

        AddAndSearchWord obj = new AddAndSearchWord();
       obj.addWord("bad");
       obj.addWord("dad");
        obj.addWord("mad");

      System.out.println(obj.search("bad"));
        System.out.println(obj.search(".ad"));

    }
}


