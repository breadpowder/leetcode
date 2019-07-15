package question


/*

 Palindromic Substrings
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
Note:


 */


// Dynamic programming with subsequence,

//D(i,j) =  D(i-1, j-1) and D(i)=D(j)
object PalindromicSubstrings {


  def countSubstrings(s: String): Int = {

    if(s==null || s.isEmpty) return 0
    val len = s.length

    val result = Array.ofDim[Boolean](len, len)

    //For odd
    for{
      i <- 0 to len -1
    }{
      result(i)(i) = true
    }


    for(i <- 1 to len - 1){

      var stop = false
    for{
      j <- 1 to Math.min(i,len-1-i)
      if !stop
    }{

      if(s(i-j) == s(i+j)){
          result(i-j)(i+j) = true
      }
      else
        {
          stop = true
        }
    }
    }

    //For even
/*    for{
      i <- 0 to len -2
    }{
      if(s(i)==s(i+1))
      result(i)(i+1) = true
    }*/

    for(i <- 0 to len - 1){
      var stop = false
      for{
        j <- 0 to Math.min(i,len-2-i)
        if !stop
      }{

        if(s(i-j) == s(i+j+ 1)){
          result(i-j)(i+j +1) = true
        }
        else
        {
          stop = true
        }
      }
    }



/*  for{
      i<- 0 to len-1
      j <- 0 to len-1
      if result(i)(j) == true
    }{

      println(s.substring(i,j+1))

    }*/


    result.map(a => a.filter(_==true).size).reduce(_ + _)


    }






  def main(args: Array[String]): Unit = {

 println(countSubstrings("aaa"))

   // assert(countSubstrings("aaa")==6)


    println(countSubstrings("abccba"))

    //assert(countSubstrings("abccba")==9)

    println(countSubstrings("xkjkqlajprjwefilxgpdpebieswu"))
  }

}
