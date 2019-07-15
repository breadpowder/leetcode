package question.bitwise

import scala.annotation.tailrec

/*
https://www.geeksforgeeks.org/russian-peasant-multiply-two-numbers-using-bitwise-operators/
Given two integers, write a function to multiply them without using multiplication operator.
 (Multiply two numbers using bitwise operators)
Given two integers, write a function to multiply them without using multiplication operator.
There are many other ways to multiply two numbers (For example, see this).
One interesting method is the Russian peasant algorithm. The idea is to double the first number
and halve the second number repeatedly till the second number doesn’t become 1.
In the process, whenever the second number become odd, we add the first number to result (result is initialized as 0)
The following is simple algorithm.

 */
object RussianPeasant {



  @tailrec
  def russianPeasant(a: Int, b: Int, residual: Int =0 ): Int ={

   // Step: if  b even, double a and half b
    // else a add a, minus b and

      if(b<0) russianPeasant(-a,-b,residual)

      else  if (b == 0) return 0

      else if(b==1) return a + residual

      else if ((b & 1) == 0) return russianPeasant(a<<1, b>>1, residual)

      else {

        russianPeasant(a, b - 1, residual + a)
      }

    }




  def main(args: Array[String]): Unit = {

  println(russianPeasant(5,15))

    println(russianPeasant(-6, 5))

    println(russianPeasant(5, -6))



  }
}
