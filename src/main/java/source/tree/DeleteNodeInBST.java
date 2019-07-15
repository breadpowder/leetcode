package source.tree;

/*

450.Delete Node in a BST

Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7
 */

//Take away: Do not need parent in the call stack,

public class DeleteNodeInBST {


     public static class TreeNode {
     int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

        public TreeNode deleteNode(TreeNode root, int key) {

            return deleteRec(root,root,key);

        }


   public TreeNode cleanRec(TreeNode root, int key){


       if(root==null) return null;

       if(key<root.val) root.left = deleteRec(root.left, root, key);

       else if(key>root.val)  root.right = deleteRec(root.right, root, key);

       else{

           if(root.right!=null){

               TreeNode mostLeft = findLeft(root.right);
               mostLeft.left = root.left;


               return root.right;
           }

           else{

               return root.left;

           }
       }

        return  root;
   }


    public TreeNode cleanTwo(TreeNode root, int key){


        if(root==null) return null;

        if(key<root.val) root.left = deleteRec(root.left, root, key);

        else if(key>root.val)  root.right = deleteRec(root.right, root, key);

        else{


            if(root.right!=null){

                TreeNode mostLeft = findLeft(root.right);
                mostLeft.left = root.left;


                return root.left;

            }



            if(root.left!=null){

                root.left.right = root.right;
                return root.left;

            }

            else{
                return root.right;

            }
        }

        return  root;
    }



    public TreeNode deleteRec(TreeNode root, TreeNode parent, int key){

        if(root==null) return null;

        if(key<root.val) deleteRec(root.left, root, key);

        else if(key>root.val)  deleteRec(root.right, root, key);


        else{


            //if root.right is not null. a. set root.left to the most left of root.right b. connect parent to root's child
            if(root.right!=null){

                TreeNode mostLeft = findLeft(root.right);
                mostLeft.left = root.left;

                if(parent!=null)  {

                    if(root == parent.left) {
                        parent.left = root.right;
                    }

                    else{
                        parent.right = root.right;
                    }


                }
                return root.right;
            }

            else{

                //if root.right is null, update parent's child
                if(parent!=null)  {

                    if(root == parent.left) {
                        parent.left = root.left;
                    }

                    else{
                        parent.right = root.left;
                    }
                    //return root.left;



                }

                return root.left;

            }


        }

        return root;

    }




        public TreeNode findLeft(TreeNode node){

            while(node.left!=null)
            {
                node = node.left;
            }

            return node;

        }


        public static void main(String[] args){

             DeleteNodeInBST bst =   new DeleteNodeInBST();

             TreeNode root = new TreeNode(0);

             TreeNode deleted = bst.deleteNode(root, 0);
        }
    }

