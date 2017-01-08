package shenzhen.compiler

import scala.util.parsing.combinator.Parsers
import shenzhen.language.Statement
import scala.util.parsing.input.Reader
import scala.util.parsing.input.Position
import scala.util.parsing.input.NoPosition
import shenzhen.language._

object Parser extends Parsers {

  override type Elem = Token

  class TokenReader(tokens: Seq[Token]) extends Reader[Token] {
    override def first: Token = tokens.head
    override def atEnd: Boolean = tokens.isEmpty
    override def pos: Position = NoPosition
    override def rest: Reader[Token] = new TokenReader(tokens.tail)
  }

  private def numeric: Parser[NUMERIC] = {
    accept("numeric", { case value @ NUMERIC(name) => value })
  }

  private def identifier: Parser[IDENTIFIER] = {
    accept("identifier", { case id @ IDENTIFIER(name) => id })
  }

  private def label: Parser[Label] = {
    identifier ~ COLON ^^ { case id ~ _ => Label(id.str) }
  }

  private def value: Parser[Value] = {
    (identifier | numeric) ^^ {
      case IDENTIFIER(str)   => Register(str)
      case NUMERIC(numValue) => Constant(numValue)
      case otherToken        => throw new IllegalArgumentException(s"Unexpected token: $otherToken - this should not happen!")
    }
  }

  private def mov: Parser[Mov] = {
    MOV ~> value ~ value ^^ { case from ~ to => Mov(from, to) }
  }

  private def add: Parser[Add] = {
    ADD ~> value ^^ Add
  }

  private def sub: Parser[Sub] = {
    SUB ~> value ^^ Sub
  }

  private def jmp: Parser[Jmp] = {
    JMP ~> identifier ^^ { case to => Jmp(to.str) }
  }

  private def instruction: Parser[Instruction] = {
    mov | add | sub | jmp
  }

  private def labeledInstruction: Parser[LabeledInstruction] = {
    label ~ instruction ^^ { case l ~ i => LabeledInstruction(l, i) }
  }

  private def statement: Parser[Statement] = {
    (label | labeledInstruction | instruction) <~ CRLF
  }

  private def program: Parser[Program] = {
    rep1(statement) ^^ Program
  }

  def parse(tokens: Seq[Token]): Either[ParsingError, Program] = {
    val reader = new TokenReader(tokens)
    program(reader) match {
      case NoSuccess(msg, next)  => Left(ParsingError(msg))
      case Success(result, next) => Right(result)
    }
  }

}
