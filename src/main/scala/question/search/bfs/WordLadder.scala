package question.search.bfs


/*
127

Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest
 transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
object WordLadder {

  def ladderLength(beginWord: String, endWord: String, wordList: List[String]): Int = {

    var wordSet = wordList.toSet

    val chars: Seq[Char] = (0 to 25).map(i => ('a' + i).toChar) //    for( idx <-  'a' to 'c') to generate ch



    //STEP 1  initiate
    var queue = collection.mutable.Queue[String]()
    queue.enqueue(beginWord)

    var steps = 1

    //STEP 2  stop condition,  Note: optimize early exit is wordSet is empty
    while(queue.nonEmpty && wordSet.nonEmpty) {

      steps += 1

      // Swap a new queue
      val newQueue = collection.mutable.Queue[String]()

      //STEP 3  expand queue by visiting each node in queue
      // use array to save mutate cost
      while (queue.nonEmpty) {

        val w = queue.dequeue()

        for (idx <- 0 until w.length) {

          val beforeChar = w.charAt(idx)

          val wordArr = w.toCharArray

          chars.foreach { ch =>

            if (beforeChar != ch) {

              wordArr(idx) = ch
              val mutated = wordArr.mkString

              //STEP4  stop condition fast
              if (mutated == endWord) {
                return steps
              }
              if (wordSet.contains(mutated)) {

                newQueue.enqueue(mutated)
                //optimize by throwing away visited nodes
                wordSet = wordSet - mutated
              }

              wordArr(idx) = beforeChar
            }
          }
        }
      }
      queue = newQueue
    }
    0
  }


  def ladderLengthBiBFS(beginWord: String, endWord: String, wordList: List[String]): Int = {

    //how to get a mutable set???

    // answer: collection.mutable.HashSet(wordList:_*)
    var wordSet = wordList.toSet

    if(!wordSet.contains(endWord)) return  0

    import collection.mutable.Queue

    var q1 = Queue[String]()
    q1.enqueue(beginWord)

    var q2= Queue[String]()
    q2.enqueue(endWord)

    val chars = (0 to 25).toList.map(i =>  ('a' + i).toChar)

    var steps = 2

    //stop if queust is empty
    while(q1.nonEmpty && wordSet.nonEmpty){

      val anotherSet = q2.toSet
      val newQueue = Queue[String]()

      //expand queue TODO always expand smaller queue for speeding up
      while(q1.nonEmpty){

        val w  = q1.dequeue
        for(idx <-  0 until w.length)
        {
          val beforeChar = w(idx)
          chars.foreach
          { ch =>
            val charArr = w.toCharArray
            if(beforeChar!=ch)
            {
              charArr(idx)=ch
              val mutated = charArr.mkString
              if(anotherSet.contains(mutated))
              {
                return steps
              }
                if(wordSet.contains(mutated)){
                  newQueue.enqueue(mutated)
                  wordSet -= mutated
                }
              charArr(idx)=beforeChar
            }

          }
        }
      }
      steps +=1
      //This isn't simply "multiple variable assignment",

      //how to assign tuple (q2, q1) = (newQueue, q2), this should be atmoic operation, assign order mattes if asssign to q2 first,

      q1 = q2
      q2 = newQueue

    }
    0
  }





  def main(args: Array[String]): Unit = {

   val test1 = List("hot","dot","dog","lot","log","cog")

    println(ladderLengthBiBFS("hit","cog",test1))

   val test2 = List("hot","dot","dog","lot","log")

    println(ladderLengthBiBFS("hit","cog",test2))

    val test3= List("a","b","c")

    println(ladderLengthBiBFS("a","c",test3))






  }
}
