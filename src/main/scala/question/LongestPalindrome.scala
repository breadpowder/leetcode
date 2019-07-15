package question


/*5. Longest Palindromic Substring, Link to question
 Palindromic Substrings


Share
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"*/

object LongestPalindrome {

  def longestPalindrome(s: String): String = {

    if(s.isEmpty) return ""

    val len = s.length
    val table = Array.ofDim[Int](len, len)


    def populatePalandromeFrom(begin: Int, e: Int) = {

      var start = begin
      var end = e

      while (start >= 0 && end <= len - 1) {

        if (table(start + 1)(end - 1) == 1 && s.charAt(start) == s.charAt(end)) {
          table(start)(end) = 1
        }

        else {
          table(start)(end) = -1
        }

        start -= 1
        end += 1
      }

    }


    for {
      i <- 0 to len - 2
    } {
      table(i)(i) = 1
      if (s(i) == s(i + 1)) table(i)(i + 1) = 1
      else table(i)(i + 1) = -1
    }

    table(len - 1)(len - 1) = 1

    for {i <- 0 to len - 1} {

      populatePalandromeFrom(i - 1, i + 1)
      populatePalandromeFrom(i - 1, i + 2)

    }

    var maxStart = 0
    var maxEnd = 0

    for {
      i <- 0 to len - 1
      j <- i to len - 1
      if (table(i)(j) == 1)
    } {

      if (j - i > maxEnd - maxStart) {
        maxEnd = j
        maxStart = i
      }
    }

    s.substring(maxStart, maxEnd+1)
  }


    def main(args: Array[String]): Unit = {



      println(longestPalindrome("acdedcf"))

      println(longestPalindrome("acdeedcf"))


      println(longestPalindrome("abcde"))

    }



}
