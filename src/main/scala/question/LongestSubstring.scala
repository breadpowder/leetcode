package question

/*
3;
Given a string, find the length of the longest substring without repeating characters.


Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

*/


//Can use double deque (dobule linked array), deque has a limitation of contains(char)
//TODO: don't use break clause instead, https://leetcode.com/problems/longest-substring-without-repeating-characters/discuss/241221/javaO(n)-Sliding-window-with-a-set

object LongestSubstring {

 def lengthOfLongestSubstring(s: String): Int = {

   s.length match {


     case 0 | 1 => s.length

     case _ => {
       var start, end = 0
       var maxLength = 0

       val charSet = collection.mutable.Set[Char]()

       import scala.util.control.Breaks._
       for (i <- 0 to s.length - 1 if end != s.length-1 ) {
         breakable {
           for (j <- Math.max(start, i) to s.length - 1) {
             val ch = s.charAt(j)
             end = j
             if (charSet.contains(ch)) {
               start = j
               //TODO pay attention to the order of remove and where to skip
               maxLength = Math.max(charSet.size, maxLength)
               if (j != s.length - 1) charSet.remove(s.charAt(i))
               break
             }
             else {
               charSet.add(ch)
             }
           }
         }
       }
       maxLength = Math.max(charSet.size, maxLength)
       maxLength
     }
   }
 }



  /* abcdefcghji...*z */

/*
    def lengthOfLongestSubstring(s: String): Int = {

      s.length match {


        case 0 | 1 => s.length

        case _ => {
          var idx, start, maxLength = 0

          var charSet = Array.fill[Int](26)(-1)

          while (idx < s.length) {
            val ch = s.charAt(idx)
            val marker = ch - 'a'

            if (charSet(marker) != -1) {

              if (idx - start > maxLength) maxLength = idx - start


              start = charSet(marker) + 1

              charSet(marker) = idx

              charSet = Array.fill[Int](26)(-1)


            }
            else {
              charSet(marker) = idx
              maxLength += 1

            }

            idx += 1
          }
          maxLength
        }
      }
    }

*/






  //pwkpe
  //pwkw remove p
  //pwwk
  def main(args: Array[String]) : Unit = {



    assert (lengthOfLongestSubstring("abcdefcghji")==8,lengthOfLongestSubstring("abcdefcghji"))


    assert (lengthOfLongestSubstring("pwkpe")==4,lengthOfLongestSubstring("pwkpe"))
       println(lengthOfLongestSubstring("pwkw"))

        assert(lengthOfLongestSubstring("pwwk")==2)


        assert(lengthOfLongestSubstring("abcadbefe")==6)

        assert(lengthOfLongestSubstring("pwwkew")==3)

       assert(lengthOfLongestSubstring("ohomm")==3)


  }
}
