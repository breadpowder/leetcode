/*17. Letter Combinations of a Phone Number
Medium

2088

287

Favorite

Share
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

  A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



  Example:

  Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].*/

object QuickSolution {

  //interger toChar has potential problem, use string instead

  // if generator use map for for, otherwise foreach
  // Integer to char 'a' + k ?
  // PAY ATTENTION TO VARIABLE IN THE subclass
  val map: Map[String, List[Char]] = {
    val maps = (2 to 9).map{
      i => {
        val k = 3*(i-2)
        (i.toString -> List(('a'+ k).toChar, ('a' + k+1).toChar, ('a' + k +2 ).toChar))
      }
    }.toMap

    maps ++ Map("7" -> List('r','p', 'q','s'),

      "8" -> List('t','u','v'),

      "9" -> List('w','x','y','z')

    )
  }

  def letterCombinations(digits: String): List[String] = {

    var result: List[String] = List.empty[String]


    def dfs(subStr: String, currentPath: List[Char] =List.empty ): Unit ={

      if(subStr.isEmpty) {

        if (! (currentPath isEmpty)) {
          result = currentPath.mkString.reverse :: result
        }
      }

      else{
        val head = subStr.head.toString
        val remaining = subStr.substring(1)

       // println(head)
        map.getOrElse(head,List.empty[Char]).foreach{ char =>
          dfs(remaining, char :: currentPath)
        }
      }

    }

    dfs(digits)

    result
  }


  def main(args: Array[String]): Unit = {

 /*   letterCombinations("23").foreach{
      s => println(s)
    }*/

    letterCombinations("7").foreach{ s=>

      println(s)
    }
  }
}