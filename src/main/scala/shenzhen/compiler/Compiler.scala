package shenzhen.compiler

import shenzhen.language.Program
import shenzhen.lexer.Lexer
import shenzhen.parser.Parser

object Compiler {

  val lexer = Lexer
  val parser = Parser

  def compile(input: String): Either[CompilationError, Program] = {
    for {
      tokens <- lexer.tokenise(input).right
      program <- parser.parse(tokens).right
    } yield {
      program
    }
  }

}
