package question

/*Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

1         3     3      2      1
\       /     /      / \      \
3     2     1      1   3      2
/     /       \                 \
2     1         2                 3*/


// Cn = c0Cn-1 + C1Cn-2 +C2C
object UniqueBinarySearchTree {

  def numTrees(n: Int): Int = {


      val result = Array.ofDim[Int](n+1)
      result(0) = 1

      for{
        i <- 1 to n
      }{

        for{j <- 0 to i-1

        }
        result(i) += result(j)* result(i-1-j)
      }

    return result(n)

  }

  def main(args: Array[String]) =
  {
    println(numTrees(4))
    println(numTrees(5))
  }
}
