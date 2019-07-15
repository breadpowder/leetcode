package source;

import java.util.List;

import java.util.Arrays;


/*

71.
Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.

In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level. For more information, see: Absolute path vs relative path in Linux/Unix

Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.



Example 1:

Input: "/home/"
Output: "/home"
Explanation: Note that there is no trailing slash after the last directory name.
Example 2:

Input: "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
Example 3:

Input: "/home//foo/"
Output: "/home/foo"
Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
Example 4:

Input: "/a/./b/../../c/"
Output: "/c"
Example 5:

Input: "/a/../../b/../c//.//"
Output: "/c"
Example 6:

Input: "/a//b////c/d//././/.."
Output: "/a/b/c"
 */

//take away,

// 1. can build a empty root node and build each level using name + "/"
//2.  Pointer state. if  encounter ../, besides move the prev pointes and  need to reset prev, to null, since current should be

public class SimplifyPath {

    class Solution {

        public class Node {

            Node prev;

            Node next;

            String name;

            Node(String _name){

                this.name = _name;
            }
        }

        public String simplifyPath(String input) {

            if(input==null || input.isEmpty()) return input;

            //String[] levels =
            //LinkedList<Node> nodes= new LinkedList<Node>();


            List<String> levels = Arrays.asList(input.split("/"));

            Node pre = new Node("");

            Node root = pre;

            for(String path: levels){

                //isroot
                if(path.isEmpty()) continue;

                if(path.equals(".")) continue;

                if(path.equals("..") && pre.name.equals("")) continue;

                if(path.equals("..")) {
                    pre = pre.prev;
                    pre.next = null;
                    continue;
                }
                // System.out.println("path:" + path);

                Node cur = new Node(path);
                cur.prev = pre;
                pre.next = cur;

                pre = cur;

            }

            StringBuilder sb = new StringBuilder();

            sb.append("/");

            //System.out.println("root:" + root.name);
            root= root.next;
            while(root!=null){

                if(root.next!=null)
                    sb.append(root.name + "/");
                else
                    sb.append(root.name);

                root =  root.next;

            }

            // System.out.println(sb.toString());

            return sb.toString();

        }
    }
}
