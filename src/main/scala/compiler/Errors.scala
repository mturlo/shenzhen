package compiler

case class LexicalError(token: Token)

case class ParsingError(token: Token)
