package source.tree;

/*
109. Convert Sorted List to Binary Search Tree

Favorite

Share
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted linked list: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5

 */


//take away: see prev, which is before the middle and can be used to split the nodes into two halves

public class ConvertedSortedListToBinaryTree {


      public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }


      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }


    public TreeNode sortedListToBST(ListNode head) {

        if(head==null) return null;

        //if(head.next==null) return new TreeNode(head.val);


        ListNode slow = head;

        ListNode fast = head;

        ListNode prev= head;

        while(slow.next!=null && fast != null && fast.next!=null){

            prev = slow;

            slow = slow.next;

            fast = fast.next.next;

        }


        TreeNode node = new TreeNode(slow.val);

        ListNode high = slow.next;

        //TODO set pointer of prev to null, so we can search the sublist
        prev.next = null;

        //TODO edge case for single node to ensure the node has child
        if(prev==slow) node.left = null;

            //System.out.println(node.val + "|"  + head.val);// + "|" + high.val);
        else{
            node.left = sortedListToBST(head);
            node.right = sortedListToBST(high);

        }



        return node;


    }

}
