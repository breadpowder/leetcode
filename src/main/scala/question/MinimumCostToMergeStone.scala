package question

/*
1000 Minimum Cost to Merge Stones
There are N piles of stones arranged in a row.  The i-th pile has stones[i] stones.

A move consists of merging exactly K consecutive piles into one pile, and the cost of this move is equal to the total number of stones in these K piles.

Find the minimum cost to merge all piles of stones into one pile.  If it is impossible, return -1.

Example 1:

Input: stones = [3,2,4,1], K = 2
Output: 20
Explanation:
We start with [3, 2, 4, 1].
We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
We merge [4, 1] for a cost of 5, and we are left with [5, 5].
We merge [5, 5] for a cost of 10, and we are left with [10].
The total cost was 20, and this is the minimum possible.
Example 2:

Input: stones = [3,2,4,1], K = 3
Output: -1
Explanation: After any merge operation, there are 2 piles left, and we can't merge anymore.  So the task is impossible.
Example 3:

Input: stones = [3,5,1,2,6], K = 3
Output: 25
Explanation:
We start with [3, 5, 1, 2, 6].
We merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6].
We merge [3, 8, 6] for a cost of 17, and we are left with [17].
The total cost was 25, and this is the minimum possible.


[3,5,1,2,6, 7, 4]
Note:

1 <= stones.length <= 30
2 <= K <= 30
1 <= stones[i] <= 100
*/

object MinimumCostToMergeStone {

  def mergeStones2(stones: Array[Int], K: Int): Int = {

    //last one to merge for k=2: DP(i,j) = sum(i,j) + max{DP(i,k) +  DP(k+1,j) for k from i + 1 to j-1}
    // Orignial problem DP(0, N-1), Initiate DP(m, m+k-1) = sum(m, m+k-1)


    //TODO how about K =3
    val len = stones.length

    val dp: Array[Array[Integer]] = Array.fill(len, len)(Integer.MAX_VALUE>>1)

    var initCost = stones.slice(0, K-1).sum

    //initiate
    for(i <- 0 to len - K){
      initCost += stones(i + K - 1)
      dp(i)(i + K -1) = initCost
      initCost -= stones(i)
    }

    for{
      width <- K+1 to len
      i <- 0 to len - K
      if i + width - 1 < len
    }{

      //compute width i.e. from (i, i+ width -1) inclusive
      dp(i)(i+ width-1) = stones.slice(i,i+ width).sum

      val minCost = {

        var min = Math.min(dp(i+1)(i + width -1), dp(i)(i+width-2))


        for (m <- i+K -1 until i + width -K ){

             min = Math.min(dp(i)(m) + dp(m+1)(i+width-1), min)
        }

        min
      }

      dp(i)(i+ width-1) += minCost

      println(i + "," +  (i+ width -1) + ",cost:" + dp(i)(i+width-1))
    }

    dp(0)(len-1)

  }

  def mergeStones(stones: Array[Int], K: Int): Int = {

    val len = stones.length

    if ((len-1) % (K-1) !=0) return -1

    val dp = Array.fill(len, len, K+1)(Integer.MAX_VALUE)

    for(i <- 0 to len-1){
      dp(i)(i)(1) = 0
    }


    //val parentPoint = Array.ofDim(len, len, K+1)[Tuple2[List[Int],List[Int]], String]

    var globalPath = List.empty[String]
    //dp[i][j][1] = dp[i][j][K] + sum[i][j] (dp[i][j][K] != max)
    //dp[i][j][k] = min(dp[i][t][k - 1] + dp[t + 1][j][1]) (t ∈ [i, j) && dp[i][t][k - 1] != max && dp[t+1][j][1] != max && k ∈ [2, K])
    //TODO dp[i][t][1] must valid merge, for example, if K=5, (10,12,1) IS NOT A VALID merge
    for{
      width <- 2 to len
      i <- 0 to len - width
      j = i + width -1 if j < len
    }{
      var path = ""

      for{
        k <- 2 to K
        t <- i to j-1
        if k <= width
        if dp(i)(t)(k-1)!=Integer.MAX_VALUE && dp(t+1)(j)(1) != Integer.MAX_VALUE
      } {
        val candidate  = dp(i)(t)(k - 1) + dp(t+1)(j)(1)

        if(candidate<dp(i)(j)(k)) {
          dp(i)(j)(k) = candidate
          path = s"merge {$i,$t,${k-1}} with {${t+1}, $j, 1}"
        }
      }

      //println("i,j,k:" + List(i,j,k).mkString(",") + "," + min)
      if(dp(i)(j)(K) != Integer.MAX_VALUE) {
        dp(i)(j)(1) = dp(i)(j)(K) + stones.slice(i, j + 1).sum
        path = path + s", result {$i,$j,1}, ${dp(i)(j)(1)}"
        globalPath = path :: globalPath
      }

      globalPath.reverse.foreach(println)
      //println("i,j,1:" + List(i,j,1).mkString(",") + "," + dp(i)(j)(1))

    }



    dp(0)(len-1)(1)

  }
    def main(args: Array[String]): Unit = {

/*      val test0 = Array(3,5,4,1)

      assert(mergeStones(test0,3)== -1)

    val test1 = Array(3,5,1,2,6)

    val result1= mergeStones(test1, 3)

    assert(result1 == 25, result1)

    val test2 = Array(4,3,3,4)

    val result2= mergeStones(test2, 2)


    assert(result2==28, result2)*/

      val test4 = Array(36,2,61,30,74,35,65,31,43,92,15,11,22)

      val result4 = mergeStones(test4,5)
      assert(result4==902, result4)

  }
}

