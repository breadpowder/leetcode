package source;

/*

212
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.



Example:

Input:
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]


Note:

All inputs are consist of lowercase letters a-z.
The values of words are distinct.
 */

import java.util.List;

import java.util.LinkedList;

public class WordSearchII {

    int[] next = {-1,0,1,0,-1};

    public List<String> findWords(char[][] board, String[] words) {




        List<String> result  = new LinkedList<>();

        if(board==null||board.length==0) return result;

        //word should be in the most outer loop to skip

        //TODO also, IF ["a","a"] with searchword "a", should be only come out once, not twice, should break all loop

        for(String word: words){
            boolean isFound = false;
            for(int i=0; i<board.length; i++){
                if(isFound){ break;}
                for(int j=0; j<board[0].length; j++)


                    if(canFind(board, i, j, word, 0)){
                        result.add(word);
                        isFound = true;
                        break;
                    }
            }

        }


        return result;
    }


    boolean canFind(char[][] board, int i, int j, String word, int c){

        if(c==word.length()) return true;

        if(i<0 || i>= board.length || j<0 || j>=board[0].length) return false;


        if(board[i][j] == 'A') return false;

        if(board[i][j] == word.charAt(c)){

            for(int idx=0; idx<4; idx++){

                int next_i= i+next[idx];

                int next_j = j+next[idx+1];


                char ch = board[i][j];

                //TODO set [i][j] not next_i and next_j, thinking about this, the first one needs to be set
                board[i][j] = 'A';
                if(canFind(board,next_i, next_j, word, c+1)) {

                    board[i][j] = ch;
                    return true;
                }

                board[i][j] = ch;


            }

        }

        return false;

    }
}
