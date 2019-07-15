package source;

import java.util.List;

import java.util.LinkedList;

//212. Word Search II
//Hard
//
//1125
//
//68
//
//Favorite
//
//Share
//Given a 2D board and a list of words from the dictionary, find all words in the board.
//
//Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
//
//
//
//Example:
//
//Input:
//board = [
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//words = ["oath","pea","eat","rain"]
//
//Output: ["eat","oath"]

//take away: pay attention to how trie help prune branch
// should use Set to prevent "a" found {"a","a"} multiple times due to it loops the board

public class WordSearchIITrie {


    int[] next = {-1,0,1,0,-1};


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

            p.word = w;

        }
    }


    public static class TrieNode{


        public TrieNode[] child = new TrieNode[26];

        public String word;

        public TrieNode(){

        }

        public TrieNode addChild(char c){

            int idx =  c - 'a';

            if(child[idx]==null) child[idx] = new TrieNode();

            return child[idx];
        }
    }

    public List<String> findWords(char[][] board, String[] words) {

        List<String> result  = new LinkedList<>();

        if(board==null||board.length==0) return result;

        Trie trie = new Trie();

        for(String word: words){

            trie.addWord(word);
        }

        //word should be in the most outer loop to skip

        //TODO also, IF ["a","a"] with searchword "a", should be only come out once, not twice, should break all loop


        for(int i=0; i<board.length; i++)
            for(int j=0; j<board[0].length; j++)
                canFind(board, i, j, trie.root, result);

        return result;
    }


    void canFind(char[][] board, int i, int j, TrieNode root, List<String> result){

        if(i<0 || i>= board.length || j<0 || j>=board[0].length) return;

        char ch = board[i][j];

        if(ch == 'A') return;

        TrieNode t = root.child[ch-'a'];

        if(t!=null && t.word!=null) {
            result.add(t.word);
            //TODO do not return here, e.g. aaa is a prefix of aaab, should let it run through
            //return;
        }

        if(t!=null){

            for(int idx=0; idx<4; idx++){

                int next_i=  i + next[idx];

                int next_j = j + next[idx+1];

                //TODO set [i][j] not next_i and next_j, thinking about this, the first one needs to be set
                board[i][j] = 'A';
                canFind(board,next_i, next_j, t, result);
                board[i][j] = ch;

            }
        }

        return;

    }


    public static  void  main(String[] args){

            WordSearchIITrie trie =    new WordSearchIITrie();

           // char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
           // List<String> result = trie.findWords(board, new String[]{"eat"});

        char[][] board = {{'a','b'}, {'a', 'a'}};
        List<String> result = trie.findWords(board, new String[] {"aaa", "aaab"});//{"aba","baa","bab","aaab","aaa","aaaa","aaba"});

            for(String r: result){

                System.out.println(r);
            }


    }
}
