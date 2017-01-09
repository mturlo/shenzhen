package shenzhen.compiler

import shenzhen.language.{AST, Jmp, Label, LabeledInstruction}
import shenzhen.lexer.Lexer
import shenzhen.parser.Parser

object Compiler {

  val lexer = Lexer
  val parser = Parser

  private def checkJumpLabels(ast: AST): Either[CompilationError, AST] = {

    val jumps: Seq[Jmp] = {
      ast.statements.flatMap {
        case j: Jmp => Some(j)
        case _      => None
      }
    }

    val labels: Seq[Label] = {
      ast.statements.flatMap {
        case l: Label               => Some(l)
        case li: LabeledInstruction => Some(li.Label)
        case _                      => None
      }
    }

    val labelsInJumps: Seq[String] = jumps.map(_.to)
    val existingLabels: Seq[String] = labels.map(_.value)

    val missingLabels: Seq[String] = labelsInJumps diff existingLabels

    missingLabels match {
      case Nil               => Right(ast)
      case someMissingLabels => Left(SyntaxError(s"There are some jumps with non-existent target labels: $someMissingLabels"))
    }

  }

  private def checkSyntax(ast: AST): Either[CompilationError, AST] = {
    checkJumpLabels(ast)
  }

  def compile(input: String): Either[CompilationError, AST] = {
    for {
      tokens <- lexer.tokenise(input).right
      program <- parser.parse(tokens).right
      checkedProgram <- checkSyntax(program).right
    } yield {
      checkedProgram
    }
  }

}
