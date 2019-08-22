package newton

object SquareRoot {

  def isCloseEnough(guess: Double, f: Double => Double): Boolean =  Math.abs(f(guess)) - 0 < 1e-4

  def newTonMethod(guess: Double)(f: Double => Double, fprime: Double => Double): Unit ={

    if(isCloseEnough(guess,f)) return guess;

    val next = guess - f(guess) / fprime(guess)

    newTonMethod((guess + next)/2.0)(f, fprime);

  }

  def sqrt(num: Double): Double => Double = (x: Double) => x * x - num

  def sqrtPrime(num: Double): Double => Double = (x:Double) => 2 * x

  def main(args: Array[String]): Unit = {


    val result = newTonMethod(1.0)(sqrt(10), sqrtPrime(10))

    print(result)
  }

}
