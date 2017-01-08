import compiler.Compiler

object Shenzhen extends Compiler with App {

  val testProg =
    """
      |# some comment
      |start:
      | mov -100 acc
      | add 256
      | sub 80
      | mov acc dat
      | mov dat p0
      | jmp start #again
      |""".stripMargin

  println(testProg)

  val tokens = tokenise(testProg)
  println(tokens)

}
