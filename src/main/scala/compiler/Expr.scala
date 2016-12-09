package compiler

import scala.util.matching.Regex

sealed trait Expr

case class Instr(value: String) extends Expr

case class IntArg(value: Int) extends Expr

case class RefArg(value: String) extends Expr

case class Label(value: String) extends Expr

case class Comment(value: String) extends Expr

object Instr {

  val regex: Regex = "(mov|add|sub|mul|jmp)".r

}

object IntArg {

  val regex: Regex = "(-{0,1}\\d*)".r

}

object RefArg {

  val regex: Regex = "(\\w*)".r

}

object Label {

  val regex: Regex = "(\\w*:)".r

}

object Comment {

  val regex: Regex = "#(\\w*)".r

}
