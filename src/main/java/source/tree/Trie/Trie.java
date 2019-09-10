package source.tree.Trie;

import java.util.HashMap;
import java.util.Map;

//take away

//1. ComputeIfAbsent returns the key -> values

//2. Do delete method since it is non-trivial return deleteRecursively(root, s, -1) is a function that need to review idx starts from -1, also postOrder'



public class Trie {


    public class TrieNode {

        private Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();

        private String content;

        private boolean isWord;

        public Map<Character, TrieNode> getChildren() {
            return children;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isWord() {
            return isWord;
        }

        public void setWord(boolean word) {
            isWord = word;
        }
    }


    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String s){

        if(s == null || s.isEmpty()) return;

        TrieNode parent = root;
        TrieNode child = null;

        for(int idx=0; idx< s.length(); idx++) {

            Character ch = s.charAt(idx);
            // TODO Much better way use JAVA function interface

            child = parent.getChildren().computeIfAbsent(ch, k -> new TrieNode());

           /* parent.getChildren().putIfAbsent(ch, new TrieNode());
            child = parent.getChildren().get(ch);
*/
            child.setContent(s.substring(0, idx+1));
            parent = child;
        }

        child.setWord(true);
    }


    public boolean delete(String s){
        // has to proceed level by level
            return deleteRecursively(root, s, -1);
    }


    private boolean deleteRecursively(TrieNode node, String s, int idx){

        //if string is a only prefix of the trie path, can not delete, only update isWord to false
        if(idx==s.length() -1 && node!=null && !node.getChildren().isEmpty()){
            if(node.isWord) {
                node.setWord(false);
            }
            return false;
        }

        // string is longer than path, i.e. string not in the trie, not delete
        if(node == null && idx < s.length()){
            return false;
        }

        //reach leaf node, string length equals to node leaf
        if(node == null || node.getChildren().size() ==0 ){
            return true;
        }

        Character ch = s.charAt(idx+1);

        node = node.getChildren().get(ch);

        boolean isDeleteChild = deleteRecursively(node, s, idx+1);
        if(isDeleteChild){
            node.getChildren().remove(ch);
        }

        return node.getChildren().size() == 0;

    }


    public boolean search(String s){

        TrieNode child = root;
        for(Character c: s.toCharArray()){
            child=child.getChildren().get(c);
            if(child==null){
                return  false;
            }

        }


        return child.isWord;

    }


    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {

        TrieNode child = root;
        for(Character c: prefix.toCharArray()){
            child=child.getChildren().get(c);
            if(child==null){
                return false;
            }

        }
        return true;
    }
}
