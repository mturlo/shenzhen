package shenzhen.lexer

sealed trait Token

case class NUMERIC(value: Int) extends Token
case class IDENTIFIER(str: String) extends Token

case object COLON extends Token
case object CRLF extends Token

case object MOV extends Token
case object ADD extends Token
case object SUB extends Token
case object JMP extends Token
