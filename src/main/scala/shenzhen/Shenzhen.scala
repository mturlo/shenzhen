package shenzhen

import compiler.Compiler

object Shenzhen extends App {

  val testProg =
    """start:
      | foo: mov -100 acc
      | add 256
      | sub 80
      | mov acc dat
      | mov dat p0
      | jmp start
      |""".stripMargin

  val input = testProg
  println(input)

  val program = Compiler.compile(input)
  println(program)

}
