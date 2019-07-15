package source.search;

import java.util.HashSet;
import java.util.Set;

//130 surronded regions
/*
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */
//take away
// 1. next should be 1*5 dimension array and next_i = i + next[idx] next_j =i + next[idx+1]
// 2.  do not revist       if(toKeep.contains(idx_str))
//        {
//            return;
//        }


//seems it is slow, try BFS later
public class SurroundedRegions {

    int[] next = {1, 0, -1, 0,1};

    public void solve(char[][] board) {

        if(board.length==0) return;

        Set<String> toKeep = new HashSet<String>();

        for(int j=0; j< board[0].length; j++){
            if(board[0][j] == 'O'){
                toKeep(0, j, board, toKeep);
            }

            if (board[board.length-1][j] == 'O'){
                toKeep(board.length-1, j, board, toKeep);
            }

        }


        for(int i=0; i< board.length; i++){
            if(board[i][0] == 'O'){
                toKeep(i, 0, board, toKeep);
            }

            if (board[i][board[0].length-1] == 'O'){
                toKeep(i, board[0].length-1, board, toKeep);
            }

        }


        for(int i=0;  i< board.length; i++)
            for(int j=0; j <board[0].length; j++)
            {

                if (board[i][j] == 'O' && !toKeep.contains(createIdx(i,j))){
                    board[i][j] = 'X';
                }
            }


    }


    public String createIdx(int i, int j){

        return String.valueOf(i) + ":" + String.valueOf(j);
    }


    public void toKeep(int i, int j, char[][] board, Set<String> toKeep){


        if(i<0 || i>=board.length || j<0 || j>= board[0].length){

            return;

        }

        if(board[i][j] == 'X') return;

        String idx_str = createIdx(i,j);

        if(toKeep.contains(idx_str))
        {
            return;
        }

        toKeep.add(idx_str);


        for(int idx=0; idx <4; idx++)
        {
            int _i = i + next[idx];

            int _j = j + next[idx+1];

            toKeep(_i, _j, board, toKeep);
        }

    }

}
