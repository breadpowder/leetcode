package question


/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
*/
//TODO rewrite this CRAP

class LRUCache(_capacity: Int) {

  //TODO case class, var type. hascode and canEquals
  // TODO Entry is a customrized linked list
  //TODO Need to find Entry in O(1) and update its pointer
  // TODO many head and tail null check pointer
  //  TODO needs to handles same key overwrite, otherwise the double linked list and hashmap key is unsync

   class LinkedEntryList{

     var head :Entry = _

     var tail: Entry = _

     def size :Int = {
       var count = 0
       var next = head
       while (next!= null){
         next =next.next
         count+=1
       }
       count
     }


   }
   case class Entry(k: Int, v:Int, var prev: Entry = null, var next:Entry=null){

     override def toString: String = (k,v).toString

   }

    val cache = new scala.collection.mutable.HashMap[Int,Entry]()

  def _cache_toString= {


    println("Cache size: [" + cache.keys.size.toString + "]" + "Double size [" + doubleLinkedList.size)
  }

    val doubleLinkedList = new LinkedEntryList()

    def get(key: Int): Int = {
     // println("Getting " + key + ",")


      val result = cache.get(key)



       val v = result match {
         case Some(e: Entry) => {

           if(e.equals(doubleLinkedList.head))
           {

             return e.v
           }
           removeEntryFromDoubleLinkedList(e)

           val entry = createHeader(key,e.v)

           cache.update(entry.k,entry)

           e.v
         }
         case _ => -1
       }
     // _cache_toString
      v
    }

  private def removeEntryFromDoubleLinkedList(e:Entry): Unit ={
    val pre = e.prev
    val next = e.next
    if(next==null)
      doubleLinkedList.tail = pre
    else {
      next.prev = pre
    }
    if(pre!=null)
    {
      pre.next = next
    }
    else{
      doubleLinkedList.head = next
    }
    e.prev = null
    e.next = null
  }

  private def createHeader(k:Int, v: Int): Entry = {

    val currentHead = cache.size match {
      case 0 => null
      case _ => doubleLinkedList.head
    }

    val head = new Entry(k, v, null, next = currentHead)
    if(currentHead!=null) currentHead.prev = head
    else doubleLinkedList.tail = head
    doubleLinkedList.head = head


    head
  }

  def put(key: Int, value: Int) {
    //println("Putting " + key + "," + value)

    if(cache.contains(key)){
      val existing = cache.get(key).get
      removeEntryFromDoubleLinkedList(existing)
      cache.remove(key)
    }

      if(cache.size == _capacity){
        val currentTail = doubleLinkedList.tail
        val tail = currentTail.prev
        doubleLinkedList.tail = tail
        if(tail!=null) {
          tail.next.prev = null
          tail.next = null
        }
        cache.remove(currentTail.k)
      }



    val entry = createHeader(key,value)
    //println(doubleLinkedList.tail==null)
    cache.put(key,entry)

   // _cache_toString
  }

}

object LRUCache {

  def apply(capacity: Int): LRUCache = new LRUCache(capacity)


  def main(args: Array[String]): Unit = {

/*    val lines= TestUtility.readFile("cache.txt")

    lines(1).split(",\\[").drop(1).map( s=> s.replace("]","")).foreach(

     str=> {
       if(str.contains(","))
       {
         println(s"cache.put($str)")
       }
       else{
         println(s"println(cache.get($str))")
       }
     }
    )*/

    //testCase1




        var cache = new LRUCache( 2 /* capacity */ );

        cache.put(1, 1);
        cache.put(2, 2);
        println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        println(cache.get(2))       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        println(cache.get(1));       // returns -1 (not found)
        println(cache.get(3));       // returns 3
        println(cache.get(4));       // returns 4


     cache = new LRUCache( 1 /* capacity */ );

    cache.put(2, 1);
    println(cache.get(2));       // returns 1
    cache.put(3, 2);    // evicts key 2
    println(cache.get(2))       // returns -1 (not found)1
    println(cache.get(3));       // returns 2 (not found)












  }

  private def testCase1 = {
    val cache = new LRUCache(10 /* capacity */);
    cache.put(10, 13)
    cache.put(3, 17)
    cache.put(6, 11)
    cache.put(10, 5)
    cache.put(9, 10)
    println(cache.get(13))
    cache.put(2, 19)
    println(cache.get(2))
    println(cache.get(3))
    cache.put(5, 25)
    println(cache.get(8))
    cache.put(9, 22)
    cache.put(5, 5)
    cache.put(1, 30)
    println(cache.get(11))
    cache.put(9, 12)
    println(cache.get(7))
    println(cache.get(5))
    println(cache.get(8))
    println(cache.get(9))
    cache.put(4, 30)
    cache.put(9, 3)
    println(cache.get(9))
    println(cache.get(10))
    println(cache.get(10))
    cache.put(6, 14)
    cache.put(3, 1)
    println(cache.get(3))
    cache.put(10, 11)
    println(cache.get(8))
    cache.put(2, 14)
    println(cache.get(1))
    println(cache.get(5))
    println(cache.get(4))
    cache.put(11, 4)
    cache.put(12, 24)
    cache.put(5, 18)
    println(cache.get(13))
    cache.put(7, 23)
    println(cache.get(8))
    println(cache.get(12))
    cache.put(3, 27)
    cache.put(2, 12)
    println(cache.get(5))
    cache.put(2, 9)
    cache.put(13, 4)
    cache.put(8, 18)
    cache.put(1, 7)
    println(cache.get(6))
    cache.put(9, 29)
    cache.put(8, 21)
    println(cache.get(5))
    cache.put(6, 30)
    cache.put(1, 12)
    println(cache.get(10))
    cache.put(4, 15)
    cache.put(7, 22)
    cache.put(11, 26)
    cache.put(8, 17)
    cache.put(9, 29)
    println(cache.get(5))
    cache.put(3, 4)
    cache.put(11, 30)
    println(cache.get(12))
    cache.put(4, 29)
    println(cache.get(3))
    println(cache.get(9))
    println(cache.get(6))
    cache.put(3, 4)
    println(cache.get(1))
    println(cache.get(10))
    cache.put(3, 29)
    cache.put(10, 28)
    cache.put(1, 20)
    cache.put(11, 13)
    println(cache.get(3))
    cache.put(3, 12)
    cache.put(3, 8)
    cache.put(10, 9)
    cache.put(3, 26)
    println(cache.get(8))
    println(cache.get(7))
    println(cache.get(5))
    cache.put(13, 17)
    cache.put(2, 27)
    cache.put(11, 15)
    println(cache.get(12))
    cache.put(9, 19)
    cache.put(2, 15)
    cache.put(3, 16)
    println(cache.get(1))
    cache.put(12, 17)
    cache.put(9, 1)
    cache.put(6, 19)
    println(cache.get(4))
    println(cache.get(5))
    println(cache.get(5))
    cache.put(8, 1)
    cache.put(11, 7)
    cache.put(5, 2)
    cache.put(9, 28)
    println(cache.get(1))
    cache.put(2, 2)
    cache.put(7, 4)
    cache.put(4, 22)
    cache.put(7, 24)
    cache.put(9, 26)
    cache.put(13, 28)
    cache.put(11, 26)
  }
}



