package question



/*

question 331
One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".
 */
object VerifyTreeSerialization {

  class TreeNode(var _value: String) {
    var value: String = _value
    var left: TreeNode = null
    var right: TreeNode = null
  }

  def isValidSerialization(preorder: String): Boolean = {

  val arr = preorder.split(",")

    var idx = 0


    def traversalTree( /* nodeType:Int = -1*/): TreeNode ={
      if(idx>=arr.length)
          return null

      val value = arr(idx)
      var node = new TreeNode(value)
      idx +=1

      //TODO end condition thought a while
      //TODO this is serious wrong due to both left and right branch will return the same root
      if(value=="#") {
          return node
      }
      else {
        node.left = traversalTree()
        node.right = traversalTree()
      }

      node
    }

    def preorder_travseral(node: TreeNode):Unit ={


      if(node==null) {
        ()
      }

      else {
        println(node._value)
        preorder_travseral(node.left)
        preorder_travseral(node.right)
      }

  }


    def isValid(node: TreeNode): Boolean ={


      if(node==null) {
        return false
      }

      else {
        if(node.value == "#"){
          if(node.left==null && node.right==null)
          {
            return true
          }
          return false
        }


        isValid(node.left) && isValid(node.right)
      }

    }
    //preorder_travseral(traversalTree())
    isValid(traversalTree()) && idx == arr.length

  }

  def main(args: Array[String]): Unit = {


    println(isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"))

    println(isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,1"))

    println(isValidSerialization("9,#,4,#,#,1,#,#,2,#,6,#,#"))


    println(isValidSerialization("#,4"))



  }
}
