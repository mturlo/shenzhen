package shenzhen.compiler

import scala.util.parsing.input.Position

trait ErrorHandling {

  protected def toLocation(position: Position): Location = {
    Location(position.line, position.column)
  }

}
