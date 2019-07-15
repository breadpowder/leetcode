package question

/*

818

Your car starts at position 0 and speed +1 on an infinite number line.  (Your car can go into negative positions.)

Your car drives automatically according to a sequence of instructions A (accelerate) and R (reverse).

When you get an instruction "A", your car does the following: position += speed, speed *= 2.

When you get an instruction "R", your car does the following: if your speed is positive then speed = -1 , otherwise speed = 1.  (Your position stays the same.)

For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes to 1->2->4->-1.

Now for some nodeId position, say the length of the shortest sequence of instructions to get there.

Example 1:
Input:
nodeId = 3
Output: 2
Explanation:
The shortest instruction sequence is "AA".
Your position goes from 0->1->3.
Example 2:
Input:
nodeId = 6
Output: 5
Explanation:
The shortest instruction sequence is "AAARA".
Your position goes from 0->1->3->7->7->6.*/


/*
Analysis

DP(i) min number of steps to i
1. go (n-1) step and revert dp(i - (2^(n-1) -1) + 2^m -1 ) + n - 1 + 1 + m + 1  for each m < n-1
2. go  n step and revert dp(2^n -1 -i) + n + 1

DP(0)=0

 */

object RaceCar {

  def racecar(target: Int): Int = {

    val dp = Array.ofDim[Int](target+1)

    dp(0) = 0



   //



      for(i <- 1 to target) {
        // println((nodeId + "," + 1<<n - nodeId))

        val n = (Math.log(i) / Math.log(2)).floor.toInt + 1

        if ((1 << n) == i + 1) dp(i) = n
        else if ((1 << (n - 1)) == i + 1) dp(i) = n - 1

        else {
          val nstep = dp((1 << n) - 1 - i) + n + 1

          var min = dp(i- (1 << (n - 1))+1) + n + 1
          val nminusStep = {

            for {
              m <- 1 until n - 1
            } {
              min = Math.min(min, dp(i - (1 << (n - 1)) + (1 << m )) + n + m + 1)
            }
            min
          }
          dp(i) = Math.min(nstep, nminusStep)

        }
      }

    dp(target)
  }

  def main(args: Array[String]): Unit = {


/*    val test0 = racecar(1)
    assert(test0==1, test0)*/

/*    val test4 = racecar(2)
    assert(test4==4, test4)*/

/*
        val test1 = racecar(3)
        assert(test1==2, test1)

        val test2 = racecar(6)

        assert(test2==5, test2)
*/


    val test5 = racecar(5)

    assert(test5==7, test5)

  }
}
