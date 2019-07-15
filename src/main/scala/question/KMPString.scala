package question

/*
KMP algorithm for string comparison

pattern needs to be preprocessed so that

string :   D-A-B-C-A-B-E-A-B-D
pattern：    A-B-C-A-B-D
next:              A-B-C-A-B-D


PREPROCESSING:

        A   B  C  A  B  D
n       0   1  2  3  4  5
jump(n) -1  0  0  0  1  2


Build PREPROSESING jump

Example:

          C -A -C -A -C -C -A -C -A -C -A - C
i         0  1  2  3  4  5  6  7  8  9 10  11
jump(i)   -1 0  0  1  2  3  1  2  3  4  5  ?4
                         J              I
Use previous comparison is done:
C-A-C-A-C-A
C-A-C-A-C-C

//TODO review not-trivial for I,J index,

//TODO seems the lookup is not O(N) since multiple jumps can happen in the index

 */

object KMPString {



  def preprocess(pattern: String):Array[Int] = {


    //THIS is to solve current idx, i.e. jump[i] = ?, consider pattern[i-1] and pattern[j] (last index needs to match)
    val jump = Array.ofDim[Int](pattern.length)
    jump(0) = -1
    var j = -1

    (1 until pattern.length).foreach{
      i =>
        while (j>=0 && pattern(i-1)!=pattern(j))
        {j=jump(j)}
      j+=1
      jump(i)=j
    }


    println(jump.mkString(","))
    jump
  }

  def KMPComparision(pattern: String, text:String) : List[Int] ={

    var resultIdx = List.empty[Int]
    val jumpIdx = preprocess(pattern)


    var j = 0
    //import scala.util.control.Breaks._

    for(i <- 0 until text.length){
           while (j != -1 && text(i) != pattern(j) ) {
             j = jumpIdx(j)
           }

      if(j==pattern.length-1) {
        resultIdx = (i-j) :: resultIdx
        j = -1
      }

       j += 1

    }

    resultIdx.reverse

  }


  def main(args: Array[String]): Unit = {

     val result1 = KMPComparision("ABCABD","DABCABCABD")

    val result2 = KMPComparision("ABCABD","DABCABCABE")

    val result3 = KMPComparision("CACACCACACAC","MKCACACCACACACCACACCACACAC")



    println(result1.mkString(","))
    println(result2.mkString(","))
    println(result3.mkString(","))


  }

}
