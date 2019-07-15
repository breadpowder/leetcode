package question.bitwise

/*

A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:

For 1-byte character, the first bit is a 0, followed by its unicode code.
For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
This is how the UTF-8 encoding would work:

   Char. number range  |        UTF-8 octet sequence
      (hexadecimal)    |              (binary)
   --------------------+---------------------------------------------
   0000 0000-0000 007F | 0xxxxxxx
   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
Given an array of integers representing the data, return whether it is a valid utf-8 encoding.

Note:
The input is an array of integers. Only the least
significant 8 bits of each integer is used to store the data.
This means each integer represents only 1 byte of data.

Example 1:

data = [197, 130, 1], which represents the octet sequence: 11000101 10000010 00000001.

Return true.
It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
Example 2:

data = [235, 140, 4], which represented the octet sequence: 11101011 10001100 00000100.

Return false.
The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
The next byte is a continuation byte which starts with 10 and that's correct.
But the second continuation byte does not start with 10, so it is invalid.
 */

/*

TODO: condition and optiomize higher order function
BIT MASK use AND not xor,
if test 1
 do 10 001 and 10000  = 10000 (itself)
if test 0
   and 10000 = 0

to test pattern
1110   it means the first three digits must 1 and last digit must be 0


https://stackoverflow.com/questions/7371354/how-to-assign-a-value-to-byte-array
   Will (byte) 128 result in -1? I'm always confused with it. I know it will work in C++ (except we have to use char instead of byte), but I wasn't sure about Java. – Martijn Courteaux Sep 10 '11 at 11:31
1
@Martijn: No, (byte) 128 will result in -128. (byte) 255 will result in -1. – Jon Skeet Sep 10 '11 at 11:44
1
@Martijn byte is an 8-bit signed integer in Java, it's range goes from -128 to +127. It's stored in two's complement format. – Jesper Sep 10 '11 at 14:46
Oh, yes indeed! I meant indeed (byte) 255. Thanks. – Martijn Courteaux Sep 10 '11 at 16:08



 */

object UTF8Validation {

   val singleBytes = 1 << 7

  val twoBytes = 1 <<  7 | 1 << 6

  val threeBytes = 1 <<  7 | 1 << 6 | 1 << 5

  val fourBytes = 1 <<  7 | 1 << 6 | 1 << 5 | 1<<4

  val lowBytesRule = 1 << 7


  def getLSB(num: Int ) = num & 0x000000FF

/*

  def msBytes(data: Array[Int], idx:Int): Boolean ={

    def nextBytes(numOfBytes: Int): Boolean ={

      var startIdx = idx
      var number = startIdx
      var isValid = false
      for(i <- 1 until numOfBytes)
      {
        isValid = (lowBytesRule & number) == 0
        if(!isValid) return false

        number = data(startIdx + i)
      }

      return isValid
    }

    while(idx < data.length) {

       val n = data(idx)
      (((n & fourBytes) == fourBytes) && nextBytes(4)) ||
        (((n & threeBytes) == threeBytes) && nextBytes(3)) ||
        (((n & twoBytes) == twoBytes) && nextBytes(2)) ||
        (((n & singleBytes) == 0))

    }
  }
*/



/*  def nextBytes(bytes: Array[Bytes]): Boolean ={

    var startIdx = idx
    var number = startIdx
    var isValid = false
    for(i <- 1 until numOfBytes)
    {
      isValid = (lowBytesRule & number) == 0
      if(!isValid) return false

      number = data(startIdx + i)
    }

    return isValid
  }*/




  def validUtf8(data: Array[Int]): Boolean = {

    val arrOfBytes =  data.map(getLSB).flatMap(BigInt(_).toByteArray).filterNot(_ == Byte.box(0))


    def validateLSB(idx: Int, numOfBytes: Int): Option[Byte] ={

     arrOfBytes.slice(idx+1, idx+numOfBytes).find( v => !((1<< 7 & v) != 0 && (1<<6 & v) ==0 ))

    }


    //TODO check the most significant bytes

    var idx = 0
    while(idx< arrOfBytes.length){


      val nextByte = arrOfBytes(idx)

      if((nextByte & fourBytes) == fourBytes && ((nextByte & (1 <<3)) == 0)){

        if(idx + 3 >= arrOfBytes.length) return false

        val opt = validateLSB(idx, 4)

        if(opt.nonEmpty) return false
        idx  = idx + 4
      }


      else if((nextByte & threeBytes) == threeBytes && ((nextByte & (1 <<4)) == 0) ){

        if(idx + 2 >= arrOfBytes.length) return false

        val opt = validateLSB(idx, 3)

        if(opt.nonEmpty) return false
        idx  = idx + 3

      }


      else if((nextByte & twoBytes) == twoBytes && ((nextByte & (1 <<5)) == 0 )){

        if(idx + 1 >= arrOfBytes.length) return false

        val opt = validateLSB(idx, 2)
        if(opt.nonEmpty) return false
        idx  = idx + 2
      }




      else if((nextByte & singleBytes) == 0){

        idx = idx + 1
      }


      else{

        return false
      }
    }


    return true

  }

  def main(args: Array[String]): Unit = {
   assert(validUtf8(Array(197,
    130,
    1)))
    assert(!validUtf8(Array(235, 140, 4)))

    assert(validUtf8(Array(230,136,145)))


    assert(!validUtf8(Array(248,130,130,130)))
  }
}
