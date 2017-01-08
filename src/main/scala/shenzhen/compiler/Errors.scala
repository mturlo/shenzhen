package shenzhen.compiler

sealed trait CompilationError {

  def message: String

  def location: Location

}

case class Location(line: Int, column: Int) {

  override def toString = s"$line:$column"

}

case class LexicalError(message: String, location: Location) extends CompilationError

case class ParsingError(message: String, location: Location) extends CompilationError
