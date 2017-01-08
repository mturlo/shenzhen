package shenzhen.compiler

case class LexicalError(message: String)

case class ParsingError(token: Token)
