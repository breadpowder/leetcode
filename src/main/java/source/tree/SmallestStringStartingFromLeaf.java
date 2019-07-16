package source.tree;

import source.common.TreeNode;
/*
998
988. Smallest String Starting From Leaf

Share
Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z': a value of 0 represents 'a', a value of 1 represents 'b', and so on.

Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.

(As a reminder, any shorter prefix of a string is lexicographically smaller: for example, "ab" is lexicographically smaller than "aba".  A leaf of a node is a node that has no children.)



Example 1:



Input: [0,1,2,3,4,3,4]
Output: "dba"
Example 2:



Input: [25,1,3,1,3,0,2]
Output: "adz"
Example 3:



Input: [2,2,1,null,1,0,null,0]
Output: "abc"


Note:

The number of nodes in the given tree will be between 1 and 8500.
Each node in the tree will have a value between 0 and 25.

*/


//take away to know if it is a leaf, the only way is to p.left and p.right, remeber 0, null, 1 0 is not the leaf node
//sb reverse is place so have do twice, 2n two reverese can be optimized
public class SmallestStringStartingFromLeaf {

    public String smallestFromLeaf(TreeNode root) {

        StringBuilder result = new StringBuilder();

        dfs(new StringBuilder(), root, result);

        return result.toString();
    }


    void dfs(StringBuilder sb, TreeNode p, StringBuilder result){


        if(p==null) return;

        sb.append((char)('a' + p.val));


        if(p.left==null && p.right==null && result.length()==0){

            result.append(sb.reverse().toString());
            sb.reverse();


        }

        else if(p.left==null && p.right==null){

            if(sb.reverse().toString().compareTo(result.toString())<0){
                result.delete(0, result.length());
                result.append(sb.toString());
            }
            sb.reverse();

        }


        else{
            dfs(sb, p.left, result);
            dfs(sb, p.right, result);

        }
        sb.deleteCharAt(sb.length()-1);


    }
}
