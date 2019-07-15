
case class Value(num: Option[Int] = None, dict: Option[Map[String, Value]] = None)


object Solution {

  //for (i <- 0 until 5) println("Hello, World!")

  val result = new scala.collection.mutable.HashMap[String, Integer]

  def flatten(d :Map[String, Value]): Map[String, Integer] ={

    def flattenRecursive(v: Value, name: String= ""): Unit = {

      if (v.num.isDefined) {

        result.put(name, v.num.get)
      }


      else {
        v.dict.get.foreach { v=>
          flattenRecursive(v._2, name + "." + v._1)
        }
      }
    }


    d.foreach{
      v =>  flattenRecursive(v._2,v._1)

    }
    result.toMap
  }

  def main(args: Array[String]): Unit = {
    val test: Map[String, Value] = Map( "a" -> Value(num = Some(5)),

      "b" -> Value(num= Some(6)),
      "c" -> Value(dict = Some(Map("f" -> Value(num=Some(9))))),
      "g" -> Value(dict = Some(Map("m" -> Value(num=Some(17)),"n" -> Value(num=Some(3))))

      )
    )


    val result = flatten(test)

    result.foreach(v => println(v._1 + ":" + v._2))
  }

}