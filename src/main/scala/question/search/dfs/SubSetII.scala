package question.search.dfs

/*

90. Subsets II
Medium

846

47

Favorite

Share
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 */


/*
To sort instances by one or more member variables, you can take advantage of these built-in orderings using Ordering.by and Ordering.on:

import scala.util.Sorting
val pairs = Array(("a", 5, 2), ("c", 3, 1), ("b", 1, 3))

// sort by 2nd element
Sorting.quickSort(pairs)(Ordering.by[(String, Int, Int), Int](_._2))

// sort by the 3rd element, then 1st
Sorting.quickSort(pairs)(Ordering[(Int, String)].on(x => (x._3, x._1)))
An Ordering[T] is implemented by specifying CompareTest(a:T, b:T), which decides how to order two instances a and b. Instances of Ordering[T] can be used by things like scala.util.Sorting to sort collections like Array[T].

For example:

import scala.util.Sorting

case class Person(name:String, age:Int)
val people = Array(Person("bob", 30), Person("ann", 32), Person("carl", 19))

// sort by age
object AgeOrdering extends Ordering[Person] {
  def CompareTest(a:Person, b:Person) = a.age CompareTest b.age
}
Sorting.quickSort(people)(AgeOrdering)
 */
object SubSetII {

  def subsetsWithDup(nums: Array[Int]): List[List[Int]] = {

    //todo remmber this sortAPI
    scala.util.Sorting.quickSort(nums)

    var result =  List.empty[List[Int]]



    def subsetsRec(n: Int, curPos: Int, s: Int, cur: Array[Int]): Unit = {

      if(n==0) {

        result = cur.toList::result
      }

      else if(s>=nums.length) return

      else{


        var isFirst = true
        var pre = nums(s)

        for(idx <- s until nums.length) {

          val current = nums(idx)

          if( current!=pre || isFirst ) {
            isFirst = false
            val org = cur(curPos)

            cur(curPos) = current
            subsetsRec(n-1, curPos+1, idx+1, cur)
            cur(curPos) = org

          }

          pre = current
        }

      }
    }

    for(idx <- 1 to nums.length){
      subsetsRec(idx, 0, 0, new Array[Int](idx))
    }

    List.empty[Int] ::  result
  }

}
