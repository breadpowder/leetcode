package question

import scala.io.Source

object TestUtility {

  def time [T] (f: => T ) ={

    val start = System.currentTimeMillis()

     f

    println("Consumed: " + (System.currentTimeMillis() - start) + "ms")
  }


  def readFile(file: String) ={

    Source.fromInputStream(getClass.getClassLoader.getResourceAsStream(s"$file")).getLines().toArray
  }

  def convertToArrayChar(arrStr: Array[String]): Array[Array[Char]] ={

     return  arrStr.map( s => s.map(ch => ch).toArray)
  }

}
