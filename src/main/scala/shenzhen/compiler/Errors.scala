package shenzhen.compiler

sealed trait CompilationError {

  def message: String

}

case class LexicalError(message: String) extends CompilationError

case class ParsingError(message: String) extends CompilationError
