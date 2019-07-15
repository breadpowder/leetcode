package question.graph

/*

397. Integer Replacement

Given a positive integer n and you can do operations as follow:

If n is even, replace n with n/2.
If n is odd, you can replace n with either n + 1 or n - 1.
What is the minimum number of replacements needed for n to become 1?



Example 1:

Input:
8

Output:
3

Explanation:
8 -> 4 -> 2 -> 1
Example 2:

Input:
7

Output:
4

Explanation:
7 -> 8 -> 4 -> 2 -> 1
or
7 -> 6 -> 3 -> 2 -> 1
 */
object IntegerReplacement {


  def integerReplacement(n:Int): Unit ={
    var minCount = Integer.MAX_VALUE
    def replacement(n:Int, count: Int=0): Unit ={

      if(n==1) {
        if (count< minCount) minCount = count
      }
      //TODO use unsigned right shift
      else if ((n & 1) == 0) replacement(n>>>1, count+1)

      else {
        replacement(n-1, count+1)
        //FIXME this code explode when n is integer max, it goes to
        //https://sta.25888888888888888ckoverflow.com/questions/9397475/why-integer-max-value-1-integer-min-value
        replacement(n+1, count+1)
      }

    }


    replacement(n)
    minCount
  }



  def main(args: Array[String]): Unit = {

    assert(integerReplacement(Integer.MAX_VALUE)==32, integerReplacement(Integer.MAX_VALUE))
    assert(integerReplacement(7)==4, integerReplacement(7))

    assert(integerReplacement(1)==0)

    assert(integerReplacement(16)==4)

  }
}
