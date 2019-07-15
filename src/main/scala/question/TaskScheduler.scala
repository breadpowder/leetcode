package question

/*Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.



Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.*/
/*object TaskScheduler {

  def leastInterval(tasks: Array[Char], n: Int): Int = {

    val table = Array.ofDim[Int](26)

    task<-tasks

  }


  def main(args: Array[String]): Unit = {
    val task = Array('A','A','A','B','B','B')

    println(leastInterval(task,2))
  }
}*/
