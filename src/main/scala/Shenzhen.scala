import compiler.Compiler

object Shenzhen extends Compiler with App {

  val testProg =
    """
      |start:
      | mov -100 acc
      | add 256
      | sub 80
      | mov acc dat
      | mov dat p0
      | jmp start #again
      |""".stripMargin

  val input = args.toSeq match {
    case Nil =>
      testProg
    case nonEmptyArgs =>
      nonEmptyArgs.mkString(" ")
  }

  val tokens = tokenize(input)
  println(tokens)

  val parsed = tokens.right.map(_ map parse)
  println(parsed)

}
