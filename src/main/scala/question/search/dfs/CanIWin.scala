package question.search.dfs

/*
464. Can I Win
Medium

571

97

Favorite

Share
In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.

What if we change the game so that players cannot re-use integers?

For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.

Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.

You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.

Example

Input:
maxChoosableInteger = 10
desiredTotal = 11

Output:
false

Explanation:
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
Same with other integers chosen by the first player, the second player will always win.
 */
object CanIWin {

  //method 1 DFS, if exists a path the second player win, return false, o(n!)
  // orignal problem is to force a first play
  def canIWin(maxChoosableInteger: Int, desiredTotal: Int): Boolean = {

    def canIWinRecursive(subSet: Set[Int], remaining: Int, round: Int) : Boolean ={

      for{
        n <- subSet
        if remaining <= n
      }{

        if((round & 1) ==1) return true

        return false
      }

      //does set remove a element consant time operations? Shouldn't be
      for(next <-subSet){
        if(!canIWinRecursive(subSet - next, remaining - next, round+1)){
          return false
        }
      }

      return true
    }

    val set = (1 to maxChoosableInteger).toSet

    for(first <- set)
     if(canIWinRecursive(set - first, desiredTotal-first,2))
       {

         return true

       }

    return false
  }

  // time complexity iS o(n!)




  def canIWinDFSOptimized(maxChoosableInteger: Int, desiredTotal: Int): Boolean = {


    // easy sum
    if(maxChoosableInteger * (maxChoosableInteger+1)/2 < desiredTotal) return false

    if(desiredTotal <=0) return true

    val used = new Array[Boolean](maxChoosableInteger+1)

    val mem = collection.mutable.HashMap[String,Boolean]()

    def canIWinRecursive(remaining: Int, used: Array[Boolean]) : Boolean ={

      if(mem.contains(used.mkString)) return mem.get(used.mkString).get

      if (remaining <= 0) return false
      //does set remove a element consant time operations? Shouldn't be

      val usedToStr = used.mkString

      for(next <-1 to maxChoosableInteger) {

        if (used(next) == false) {

          used(next) = true

          if (!canIWinRecursive(remaining - next, used)) {

            //println(usedToStr)
            mem.put(usedToStr, true)
            used(next)= false
            return true
          }

          used(next)= false
        }
      }
      //}

      mem.put(usedToStr,false)

      return false
    }

    canIWinRecursive(desiredTotal,used)

/*    for(first <- set)
      if(canIWinRecursive(set - first, desiredTotal-first,1))
      {

        return true

      }*/

  }


    def main(args: Array[String]) : Unit ={


     assert(canIWinDFSOptimized(10,11)==false)

      assert(canIWinDFSOptimized(10,1))


      assert(canIWinDFSOptimized(4, 8) )



    }
}
