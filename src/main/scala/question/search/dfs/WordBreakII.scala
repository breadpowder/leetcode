package question.search.dfs

/*(

140
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
 */
object WordBreakII {


  // method 1, DFS directly, subproblems:  "something" + word
  def  wordBreakDFS(s: String, wordDict: List[String]): List[String] = {

    val dict = wordDict.toSet
    var result = List.empty[String]

    def wordBreakRecursively(s: String, pathResult: String = ""): Unit = {

      if (s.isEmpty) return

      else {

        //left is a word, add to result and continue
        if (wordDict.contains(s)) {
          result = (s + " " + pathResult) :: result
        }

        //left is not a word
        for (i <- 0 to s.length) {
          val left = s.substring(0, i)
          val right = s.substring(i)
          if (dict.contains(right)) {
            wordBreakRecursively(left, right + " " + pathResult)
          }
        }
      }
    }

    wordBreakRecursively(s)
    result

  }



    //Method 2, memorization, reccuring subproblesms: two paths can lead to the same subproblem, pineapple, solve each subproblem exactly once
    def  wordBreakDFSOptimized(s: String, wordDict: List[String]): List[String] = {

      val dict = wordDict.toSet

      val mem = new scala.collection.mutable.HashMap[String, List[String]]

      def optimizedAppend(right: String, left: List[String]): List[String] ={
        left.map(p =>  p + " " + right)
      }


      def wordBreakRecursively(s: String): List[String]  ={

        var result = List.empty[String]
        if(s.isEmpty) return List.empty[String]


        else {

          //left is a word
          if(wordDict.contains(s)){
            result = s :: result
          }

          //left is not a word
          for( i <- 1 to s.length){
            val left = s.substring(0, i)

            val right = s.substring(i)

            if(dict.contains(right)){
              result = result ::: optimizedAppend(right, wordBreakRecursively(left))
            }
          }
        }

        result
      }
      wordBreakRecursively(s)

    }


  //Method 3, use DP and build paths
  import scala.collection.mutable.ListBuffer


  def wordBreak(s: String, wordDict: List[String]): List[String] = {

    if (wordDict.isEmpty || s.size==0 ) return Nil

    val marker = Array.fill(s.length+1)(0)
    marker(0) = 1

    val wordDictArr = wordDict.toArray

    val pointers = Array.fill[List[Int]](s.length)(Nil)


    for{
        idx <- 1 until s.length+1
        wordDictIdx <- 0 until  wordDictArr.size
        val word = wordDictArr(wordDictIdx)  if idx - word.size  >=0 && s.substring(idx -  word.size, idx) == word && marker(idx-word.size)==1
    }{


      marker(idx) = 1
      pointers(idx-1) = wordDictIdx :: pointers(idx-1)
    }

    var paths = new ListBuffer[List[String]]()


    def constuctPathFromRoot(pointer: Array[List[Int]]): ListBuffer[List[String]] = {


      def constructPath(idx: Int, path: List[String]): Unit = {

        if (idx == -1) {

          paths.append(path)
          return
        }

        for (curIdx <- pointers(idx)) {

          val appendPath = wordDictArr(curIdx) :: path
          constructPath(idx- wordDictArr(curIdx).size, appendPath)
        }
      }

      for(lastIdx <-pointer.last){
        constructPath(s.length-1-wordDictArr(lastIdx).size,List(wordDictArr(lastIdx)))
      }

      paths

    }

    if(marker.last == 1){


      paths = constuctPathFromRoot(pointers)

    }

    paths.map(l=> l.mkString(" ")).toList


  }


  def main(args: Array[String]): Unit = {

   val testDict1 = List("cat", "cats", "and", "sand", "dog")

    val testS1 = "catsanddog"

    val resul1 = wordBreakDFSOptimized(testS1, testDict1)

    assert(resul1.size==2,println(resul1.mkString(",")))


    val testDict2 = List("apple", "pen", "applepen", "pine", "pineapple")

    val testS2 = "pineapplepenapple"

    val result2 = wordBreakDFSOptimized(testS2,testDict2)

    assert(result2.size==3,result2.map(s =>println(s)))


    val testDict3 = List("cats", "dog", "sand", "and", "cat")

    val testS3 = "catsandog"

    val result3 = wordBreakDFSOptimized(testS3,testDict3)

    assert(result3.size == 0,println(result3.mkString(",")))



  }
}
