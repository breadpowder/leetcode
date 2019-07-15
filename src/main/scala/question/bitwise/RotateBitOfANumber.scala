package question.bitwise

/*Rotate bits of a number
https://www.geeksforgeeks.org/rotate-bits-of-an-integer/
Bit Rotation: A rotation (or circular shift) is an operation similar to shift except that the bits
that fall off at one end are put back to the other end.
In left rotation, the bits that fall off at left end are put back at right end.

In right rotation, the bits that fall off at right end are put back at left end.

Recommended: Please try your approach on PRACTICE first, before moving on to the solution.




Example:
Let n is stored using 8 bits. Left rotation of n = 11100101 by 3 makes n = 00101111
(Left shifted by 3 and first 3 bits are put back in last ). If n is stored using 16 bits or 32 bits
 then left rotation of n (000…11100101) becomes 00..0011100101000.

Right rotation of n = 11100101 by 3 makes n = 10111100 (Right shifted by 3 and last 3 bits
are put back in first ) if n is stored using 8 bits. If n is stored using 16 bits or 32 bits
then right rotation of n (000…11100101) by 3 becomes 101000..0011100.*/

object RotateBitOfANumber {

  //1001010 left rotate 3 to 1010 100
  //1001010 right rotate 3  to 010 1001

  def roateRight(n:Int, l: Int):Int = {

    return (n >> l) | (n << (32 - l))
  }


  def main(): Unit ={

    println(roateRight(74, 3))
  }
}
