package shenzhen

import compiler.Compiler
import compiler.Lexer
import compiler.Parser

object Shenzhen extends Compiler with App {

  val testProg =
    """start:
      | mov -100 acc
      | add 256
      | sub 80
      | mov acc dat
      | mov dat p0
      | jmp start
      |""".stripMargin

  val input = testProg

  println(input)
  println(input.split("\n").size)

  val tokens = Lexer.tokenise(input)
  println(tokens)

  val program = Parser.parse(tokens.right.get)
  println(program)
  println(program.right.get.statements.size)

}
