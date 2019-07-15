package question

/*

208 Implement a trie with insert, search, and startsWith methods.

Example:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");
trie.search("app");     // returns true
Note:

You may assume that all inputs are consist of lowercase letters a-z.
All inputs are guaranteed to be non-empty strings.
 */

class Trie() {

  /** Initialize your data structure here. */

  //iS ARRAY TYPE ALWAYS INVARIANT

  abstract class Node {
    val child: Array[Inner]
  }

  case object Root extends Node {
    override  val child:  Array[Inner] = new Array[Inner](26)
  }

  case class Inner(ch: Char, var isLeaf: Boolean = false) extends Node{
    override  val child:  Array[Inner] = new Array[Inner](26)
  }



  /** Inserts a word into the trie. */
  def insert(word: String) {
    var node: Node = Root

    var idx = 0
    while (idx < word.length) {

      //TODO if exists a leaf node should not override simply befcause it marks the end of a word
      val child = {
        if (idx == word.length - 1)
            if (node.child(word(idx) - 97) == null) Inner(word(idx),true)
            else {
              val tmpNode  = node.child(word(idx) - 97)
              tmpNode.isLeaf = true
              tmpNode
            }


        else if (node.child(word(idx) - 97) == null) Inner(word(idx))

        else node.child(word(idx) - 97)
      }


      node.child(word(idx) - 97) = child
      node = child
      idx += 1
    }
  }



  def isLeaf(node:Node) = node match {
    case node: Inner => node.asInstanceOf[Inner].isLeaf
    case _ => false
  }

  def searchAll(word:String): (Boolean, Node) = {

    if(word.isEmpty) return (false,Root)

    var idx = 0
    var node: Node = Root

    while(idx<word.length){
    node match {
    case  v: Node => node = v.child(word(idx)-97)
    case  _ => return (false, node)
  }
    idx = idx+1
  }

  node match {

    case i: Inner => (true,node)
    case _ => (false,node)
  }
  }


  /** Returns if the word is in the trie. */
  def search(word: String): Boolean = {

    searchAll(word) match {

      case (false, _) => false
      case  (true, node) => isLeaf(node)
    }
  }

  /** Returns if there is any word in the trie that starts with the given prefix. */
  def startsWith(prefix: String): Boolean = {
    searchAll(prefix)._1
  }

}

object Trie {


  def main(args: Array[String]): Unit = {

    val trie = new Trie();

/*     trie.insert("apple");
    println(trie.search("apple"));   // returns true
    println(trie.search("app"));     // returns false
    println(trie.startsWith("app")); // returns true
    trie.insert("app");
    println(trie.search("app"));     // returns true

    trie.insert("app");
    trie.insert("apple");



    println(trie.search("app"))*/
    trie.insert("a");

    trie.insert("aaa");
    trie.insert("aab");
    trie.insert("aac");
    trie.insert("aad");
    trie.insert("aae");
    trie.insert("aaf");
    trie.insert("aag");
    trie.insert("aah");
    trie.insert("aai");
    trie.insert("aaj");
    trie.insert("aak");
    trie.insert("aal");
    trie.insert("aam");
    trie.insert("aan");
    trie.insert("aao");
    trie.insert("aap");
    trie.insert("aaq");
    trie.insert("aar");
    trie.insert("aas");
    trie.insert("aat");
    trie.insert("aau");
    trie.insert("aav");
    trie.insert("aaw");
    trie.insert("aax");
    trie.insert("aay");
    trie.insert("aaz");
    trie.insert("a");





    println(trie.search("aaa"))
    println(trie.search("aaaa"))

    println(trie.startsWith("aaaa"))

    println(trie.startsWith("aa"))
    println(trie.startsWith("a"))






  }
}

/**
* Your Trie object will be instantiated and called as such:
* var obj = new Trie()
* obj.insert(word)
* var param_2 = obj.search(word)
* var param_3 = obj.startsWith(prefix)
*/


