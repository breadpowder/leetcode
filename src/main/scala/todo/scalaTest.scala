package todo

object scalaTest {

  // given a list, ["a","a","b","c"] write a function to count the number of items

  //  List[("a",2), ("b",1), ("c",1)]

  def encode[T](xs : List[T]): List[(T, Int)] ={

        xs match {
          case Nil => Nil
          case x:: xs1 =>
              val (first, rest) =  xs.span(y => y==x)

            (x, first.size) :: encode(rest)
        }

  }

  def encodeFold[T](xs : List[T]): List[(T, Int)] = {

    // foldleft is not good here since it is only provide current T and B
    //   xs.foldLeft(List.empty[(T,Int)])()

    xs.foldRight(List.empty[(T, Int)]) {

      (t: T, b: List[(T, Int)]) =>
        b match {
          case Nil => List((t, 1))

          case y :: ys => if (y._1 == t) (t, y._2 + 1) :: ys else (t, 1) :: b
        }

    }
  }

//to list all elements from 1 ... M and 1...N
    def combinations(m: Int,  n: Int) = {

      (1 to m).flatMap (x => (1 to n).map(y => (x, y))).toList

    }


    def isPrime(n:Int) : Boolean  ={

      (2 to n-1).forall(n%_ !=0)

    }



  def main(args: Array[String]): Unit = {

    val a = List("a","a","b","c")

    encodeFold(a).foreach(println)

  }

}
