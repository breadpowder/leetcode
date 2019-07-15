package question.bitwise

object Subtract1FromNumber {

  def substract(n: Int) : Int ={

    var num = n
    var bit = 1

    var count =0
    // TODO should be and operator find first bit is 1, ^ slip bit leaving flip contious bit use XOR 1
    // TODO 0 is a special case without first bit, 0 -> -1 = 11111....11111

    while((num & bit) == 0 && count < 31){
        num= num ^ bit
        bit = bit << 1
        count = count +1
    }

    num ^ bit
  }

  def main(args: Array[String]): Unit = {

    println(substract(7))
    println(substract(Integer.MAX_VALUE))

    println(substract(-1))


    println(substract(0))
  }

}
