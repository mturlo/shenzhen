package compiler

import scala.util.parsing.combinator.RegexParsers

trait Lexer extends RegexParsers {

  private def numeric = "-{0,1}\\d+".r ^^ NUMERIC
  private def identifier = "[a-zA-Z][a-zA-Z0-9]*".r ^^ IDENTIFIER

  private def colon = ":" ^^ (_ => COLON)
  private def hash = "#" ^^ (_ => HASH)

  private def mov = "mov" ^^ (_ => MOV)
  private def add = "add" ^^ (_ => ADD)
  private def sub = "sub" ^^ (_ => SUB)
  private def jmp = "jmp" ^^ (_ => JMP)

  private def command = mov | add | sub | jmp
  private def symbol = colon | hash

  private def tokens = phrase(rep1(symbol | command | numeric | identifier))

  def tokenise(input: String): Either[LexicalError, List[Token]] = {
    parse(tokens, input) match {
      case NoSuccess(msg, _)  => Left(LexicalError(msg))
      case Success(tokens, _) => Right(tokens)
    }
  }

}
