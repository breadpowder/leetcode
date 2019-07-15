package question.search.dfs

/*
139 wordBreak
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s
can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false*/

object WordBreak {


  // method 1 DFS, reuse reoccuring-sub problems
  def canBuildWordDFS( s: String, wordDict: List[String]): Boolean = {

    val mem = new collection.mutable.HashMap[String, Boolean]()

    val dict = wordDict.toSet

    def dfsRecusrively(s: String): Boolean = {

      if (s.isEmpty) return true

      else if (mem.contains(s)) {
        return mem.get(s).get
      }

      else if (wordDict.contains(s)) {
        mem.put(s, true)
        return true
      }


      var canBuild = false
      for (i <- 0 to s.length) {

        val left = s.substring(0, i)
        val right = s.substring(i)


        if (dict.contains(right)) {
          if (dfsRecusrively(left)) {

            mem.put(s, true)
            return true
          }
        }

      }

      mem.put(s, false)

      return canBuild
    }

    dfsRecusrively(s)
  }




  // method 2 dp method
  def canBuildWord( s: String, wordDict: List[String]): Boolean ={


    def dp(): Boolean ={

      val arr = new Array[Boolean](s.length+1)

      arr(0) = true

      for{idx<-1 to s.length
          dicWord <-wordDict
      }{
        if(idx - dicWord.length>=0 && s.substring(idx-dicWord.length,idx)==dicWord && arr(idx-dicWord.length))
          {
            arr(idx) = true

          }

      }

      arr(s.length)
    }

    dp()
  }


  def main(args: Array[String]): Unit = {

    assert(canBuildWordDFS("catscatdogbaobao", List("cat","scat","dog","bao")))

    assert(canBuildWordDFS("catscatdogbaobao", List("cat","scat","dogD","bao"))==false)

  }

}
