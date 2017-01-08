package compiler

sealed trait Token

case class NUMERIC(str: String) extends Token
case class IDENTIFIER(str: String) extends Token

case object COLON extends Token
case object HASH extends Token

case object MOV extends Token
case object ADD extends Token
case object SUB extends Token
case object JMP extends Token
