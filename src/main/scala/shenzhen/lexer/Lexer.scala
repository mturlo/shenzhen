package shenzhen.lexer

import scala.util.parsing.combinator.RegexParsers

import shenzhen.compiler.{ErrorHandling, LexicalError}

object Lexer
  extends RegexParsers
  with ErrorHandling {

  override val whiteSpace = "[ \t\r\f]+".r

  private def numeric = "-{0,1}\\d+".r ^^ (str => NUMERIC(str.toInt))
  private def identifier = "[a-zA-Z][a-zA-Z0-9]*".r ^^ IDENTIFIER

  private def colon = ":" ^^ (_ => COLON)

  private def crlf = "\\n".r ^^ (_ => CRLF)

  private def mov = "mov" ^^ (_ => MOV)
  private def add = "add" ^^ (_ => ADD)
  private def sub = "sub" ^^ (_ => SUB)
  private def jmp = "jmp" ^^ (_ => JMP)

  private def instruction = mov | add | sub | jmp

  private def tokens = phrase(rep1(colon | instruction | numeric | identifier | crlf))

  def tokenise(input: String): Either[LexicalError, List[Token]] = {
    parse(tokens, input) match {
      case NoSuccess(msg, next) => Left(LexicalError(msg, toLocation(next.pos)))
      case Success(tokens, _)   => Right(tokens)
    }
  }

}
