package question

object JumpGame {

  def canJump(nums: Array[Int]): Boolean = {

    // for j in (len-1) to 0
    // if nums(j-1) = 0 check if j can be reached
    //    stop if can't found


    nums match {
      case Array() => false

      case Array(0) => true

      case Array(0, _*) => false


      case default => {

        import scala.util.control.Breaks._

        var hop = nums.length - 1


        while (hop > 0) {

          if (nums(hop - 1) == 0) {
            val marker = hop

            breakable {
              //TODO see the name of nexthop it's a local variable
              var nexthop = marker -1
              while(nexthop >=0) {
                if (nums(nexthop) >= marker - nexthop) {
                  hop = nexthop
                  break
                }

                if(nexthop==0)
                {
                  return false
                }

                nexthop-=1

              }
            }

          }
          else {
            hop -= 1
          }

        }
        return true
      }
    }

  }


  def main(args: Array[String]) : Unit ={

    //TODO breakable if tricy, see hop -1

    assert(canJump(Array(3,2,1,0,4))==false)

      assert(canJump(Array(6,2,5,0,0,2,0,2,0,1,2))==true)

     assert(canJump(Array(2,0,0,3,0,1,4,4)) == false)

  }
}
