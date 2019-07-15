package question.backtracking

/*
39. Combination Sum
Medium



Share
Given a set of candidate numbers (candidates) (without duplicates) and a nodeId number (nodeId),
 find all unique combinations in candidates where the candidate numbers sums to nodeId.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including nodeId) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], nodeId = 7,
A solution set is:
[
[7],
[2,2,3]
]
Example 2:

Input: candidates = [2,3,5], nodeId = 8,
A solution set is:
[
[2,2,2,2],
[2,3,3],
[3,5]
]
*/
object CombinationSumObj {

  def topDownCombinationSum(candidates: Array[Int], target: Int): List[List[Int]] = {




    //2,2,3 === 2,3,2


    var resultTable = collection.mutable.Map[Int, List[List[Int]]]()

    resultTable.put(0, List.empty)



    def combination(i: Int): List[List[Int]] = {

      var result = List[List[Int]]()

        for(candidate <- candidates)
        {

          val pre = i - candidate

          val tmp = resultTable.get(pre) match {

              //TODO if value is a empty list, cannot be cancadinate
            case Some(v) if !v.isEmpty => v.map(a => candidate :: a)
            case Some(v) if v.isEmpty => List(List(candidate))
            case _ => List.empty

          }
          result = tmp :::result
        }



      val sorted = result.map(v => v.sorted).toSet.toList

      sorted

    }

    for(i <- 2 to target)
    {

      //TODO pattern match can not match list of list
      val result = combination(i)
      result match {

        case Nil =>
        case default => resultTable.put(i,default)
      }

    }


    return resultTable.getOrElse(target, List.empty)
  }



  def main(args: Array[String]) ={

   var result = topDownCombinationSum(Array(2,3,5),8)

    print(result.mkString("\n"))

    assert(result.size==3)

    result = topDownCombinationSum(Array(2,3,6,7),7)
    print(result.mkString("\n"))

    assert(result.size==2)




  }
}
