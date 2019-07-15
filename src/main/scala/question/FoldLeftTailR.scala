package question

class FoldLeftTailR {


  def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B = {


    as match  {

      case Nil => z

      case head :: tail => foldLeft(tail,f(z, head))(f)

    }


  }
}
