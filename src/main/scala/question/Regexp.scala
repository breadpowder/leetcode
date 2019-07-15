package question

object Regexp {


  final case class RegexGroup(
                               pattern: String = ".",
                               replace: Option[String] = None
                             ) {

    lazy val effective = replace match {
      case None          => this.copy(replace = Some("*"))
      case Some("\' \'") => this.copy(replace = Some(" "))
      case Some(_)       => this
    }
  }

  def reg(regexGroups:Seq[RegexGroup], value:String): String ={

    val effectiveGroups = regexGroups.map(_.effective)

    var result = value

    effectiveGroups.foreach { group =>
      result = group.pattern.r.replaceAllIn(result, group.replace.get)
    }
    result
  }


  def main(args: Array[String]) = {



    val regexGroup = Seq(RegexGroup("(?<=^.{11,}).(?=.{0,}$)"),
      RegexGroup("(?<=^.{0,}).(?=.{19,})"))


    val value ="2018-10-12 11:11:11.111"
    println(reg(regexGroup,value))


  }

}
