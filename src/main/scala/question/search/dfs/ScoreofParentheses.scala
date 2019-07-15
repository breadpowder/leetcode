package question.search.dfs

object ScoreofParentheses {

  def scoreOfParentheses(S: String): Int = {

    //TODO this is wrong, (()(())) because only consisder "(" or ")" is not enought
    def scoreOfRecursively( sub: String,current: Int): Int ={

      // if sub is empty
      if(sub.isEmpty) return current


      val head = sub.head

      val tail = sub.substring(1)

      if(head=='(')
        return scoreOfRecursively(tail,current)

      else {
        if(current ==0)  return scoreOfRecursively(tail, 1)
        else return  scoreOfRecursively(tail, 2*current)
      }

    }



    def findFirstMatch(s: Int, e: Int):Int ={

      var b = 0

/*      import scala.util.control.Breaks._
      breakable{*/
        for{
          idx <- s to e
        }{
          val ch = S.charAt(idx)
          if(ch=='(') b+=1
          else b-=1
          if(b==0) {
            return idx
          }
        }
     // }
        return e
    }

    def scoreOfRecursion(start: Int, end: Int): Int = {

      if(start >= end) return 0


      val firstBalancedIdx = findFirstMatch(start, end)


      if(firstBalancedIdx == start + 1 ) return 1 + scoreOfRecursion(start+2, end)


      else if(firstBalancedIdx == end) return 2*scoreOfRecursion(start+1, end-1)


      else 2*scoreOfRecursion(start+1, firstBalancedIdx-1) + scoreOfRecursion(firstBalancedIdx+1,	end)


    }

    scoreOfRecursion(0, S.length-1)

  }

  def main(args: Array[String]): Unit = {

    println(scoreOfParentheses("()()"))

    println(scoreOfParentheses("(()())"))


    print(scoreOfParentheses("(()(()))"))

  }

}
