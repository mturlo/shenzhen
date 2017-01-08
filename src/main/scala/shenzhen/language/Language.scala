package shenzhen.language

sealed trait Statement

sealed trait Instruction extends Statement {

  def execute(): Unit = { // todo?

  }

}

sealed trait Value

case class Constant(value: Int) extends Value

case class Register(id: String) extends Value

case class Mov(from: Value, to: Value) extends Instruction

case class Add(value: Value) extends Instruction

case class Sub(value: Value) extends Instruction

case class Jmp(label: Label) extends Instruction

case class Label(value: String) extends Statement

case class Comment(value: String) extends Statement
