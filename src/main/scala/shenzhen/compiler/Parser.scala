package shenzhen.compiler

// import util.EitherUtils
import scala.util.parsing.combinator.Parsers
import shenzhen.language.Statement

object Parser extends Parsers {

  override type Elem = Token

  // def parse(sentence: Sentence): Either[List[ParsingError], List[Statementement]] = {
  //   EitherUtils.reduceList(sentence map parse)
  // }

  def parse(token: Token): Either[ParsingError, Statement] = {
    ???
    // token match {
    //   case Instr.regex(value) => Right(Instr(value))
    //   case IntArg.regex(value) => Right(IntArg(value.toInt))
    //   case RefArg.regex(value) => Right(RefArg(value))
    //   case Label.regex(value) => Right(Label(value))
    //   case Comment.regex(value) => Right(Comment(value))
    //   case otherwise => Left(ParsingError(otherwise))
    // }
  }

}
