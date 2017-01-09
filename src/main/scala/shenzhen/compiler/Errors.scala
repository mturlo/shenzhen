package shenzhen.compiler

sealed trait CompilationError {

  def message: String

  def location: Location

}

case class Location(line: Int, column: Int) {

  override def toString: String = {
    s"$line:$column"
  }

}

object Location {

  def zero: Location = Location(0, 0)

}

case class LexicalError(message: String, location: Location) extends CompilationError

case class ParsingError(message: String, location: Location) extends CompilationError

case class SyntaxError(message: String, location: Location = Location.zero) extends CompilationError
