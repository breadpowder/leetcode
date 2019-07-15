package question

/*
477. Total Hamming Distance
Medium
439
31


The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Now your job is to find the total Hamming distance between all pairs of the given numbers.
*/



object TotalHammingDistance {

  def totalHammingDistance(nums: Array[Int]): Int = {

    var count =0

    var bit = 1
    for (i  <- 1 to 31){
      var zeros =0

    for (num <- nums)
    {
      if((bit & num) == 0) {
        zeros +=1
      }
    }

      count += zeros * (nums.length - zeros)
      bit = bit << 1
    }


    count
  }

  def main(args: Array[String]): Unit = {

    println(totalHammingDistance(Array(4,14,2)))
  }
}
