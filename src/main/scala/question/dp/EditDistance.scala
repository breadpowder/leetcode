package question.dp

object EditDistance {


  abstract class Operation{

    val loc: Int

    val ch : Char


    val className = this.getClass.getCanonicalName
    override def toString: String = s"$className at $loc $ch"


  }

  case class Delete(loc:Int, ch: Char) extends Operation


  case class Insert(loc:Int, ch: Char, BeforeChar: Char) extends Operation{

    override  def toString:String = super.toString + s" before $BeforeChar"
  }


  case class Replace(loc:Int, ch:Char, toChar: Char) extends Operation{
    override  def toString:String = super.toString + s" with $toChar"

  }


  case class Keep(loc:Int, ch:Char) extends  Operation



  // DP(i,j)= min(
  //     DP(i+1, j+1) + replacement
  //     DP(i+1, j) + if delete x(i) e.g. ab-cde ab-de
  //     DP(i,j+1) + insert y(j)  e.g. ab-de abc-de
  // )

  /*a  d b c ?
  a
  d
  c
  b    1    1
  c 3  2 1  0
  ? 1  2 3  4*/


  def minDistance(word1: String, word2: String): Int = {

    val len1 = word1.length
    val len2= word2.length

    //TODO how to build an array for tuple2 type with dimension
    val table  = Array.fill[Int](len1+1,len2+1)(Integer.MAX_VALUE)

    val optTable = Array.ofDim[Operation](len1,len2)

    val parentPointer = Array.ofDim[(Int, Int)](len1, len2)

    //TODo expand the array

    for(j<- len2 to 0 by -1)
    {
       table(len1)(j) = len2 -j
    }

    for(i <- len1 to 0 by -1)
    {
        table(i)(len2) = len1 -i
    }

    for{
      i <- len1-1 to 0 by -1
      j <- len2-1 to 0 by -1
    } {

      //equals
      table(i)(j)=

        if (word1(i) == word2(j)) {

          parentPointer(i)(j) = (i+1,j+1)
          optTable(i)(j) = Keep(i,word1(i))
          table(i + 1)(j+1)
        }
        else {

          //TODO find the min and set the parent pointer

          val value = Math.min(Math.min(table(i+1)(j+1) , table(i+1)(j)), table(i)(j+1))

          if(value == table(i+1)(j+1)){
            optTable(i)(j) = Replace(i,word1(i), word2(j))
            parentPointer(i)(j) = (i+1,j+1)
          }

          else if(value == table(i+1)(j)){
            optTable(i)(j) = Delete(i,word1(i))
            parentPointer(i)(j) = (i+1,j)

          }
            else{
            optTable(i)(j) = Insert(i,word2(j), word1(i))
            parentPointer(i)(j) = (i,j+1)
          }

          value+1
        }

    }

/*    var i=0
    var j=0
    var ppointer = parentPointer(i)(j)

    //TODO condition should be
    while(ppointer._1 < len1 && ppointer._2< len2){

          println(optTable(i)(j))
          i = ppointer._1
          j = ppointer._2
          ppointer = parentPointer(i)(j)

    }*/


    return table(0)(0)


  }

  def main(args: Array[String]): Unit = {

    println(minDistance("intention", "iteniooon"))

   // println(minDistance("intention", "execution"))

  }

}
