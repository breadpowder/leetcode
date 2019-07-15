package question.backtracking

/*22
https://medium.com/@andreaiacono/backtracking-explained-7450d6ef9e1a
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 */

object GenerateParenthesis {



  def generateParenthesis(n: Int): List[String] = {

    var result = List.empty[String]

    //ERROR open and close is stack variable, NEED TO USE STACK VARIABLES

    def generate(s:String="", open:Int =0, close:Int =0):Unit = {


      if (close == n)
        result = s +: result

      else if (open == close) {

        generate(s + "(", open+1,close)
      }

      else if(open==n){

        generate(s + ")",open,close+1)
      }

      else if (close < open && open < n) {

          generate(s + "(",open+1,close)
          generate(s+ ")",open,close+1)
      }

    }

    generate()

    result

  }

  def main(args:Array[String]) ={

    generateParenthesis(3).foreach(println)
  }

}
