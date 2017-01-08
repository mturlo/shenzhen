package shenzhen.parser

import scala.util.parsing.combinator.Parsers
import scala.util.parsing.input.{NoPosition, Position, Reader}

import shenzhen.compiler.{ErrorHandling, ParsingError}
import shenzhen.language._
import shenzhen.lexer._

object Parser
  extends Parsers
  with ErrorHandling {

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
    (labeledInstruction | instruction | label) <~ CRLF
  }

  private def program: Parser[Program] = {
    phrase(rep1(statement)) ^^ Program
  }

  def parse(tokens: Seq[Token]): Either[ParsingError, AST] = {
    val reader = new TokenReader(tokens)
    program(reader) match {
      case NoSuccess(msg, next) => Left(ParsingError(msg, toLocation(next.pos)))
      case Success(result, _)   => Right(result)
    }
  }

}
