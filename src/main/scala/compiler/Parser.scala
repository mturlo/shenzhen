package compiler

import util.EitherUtils

trait Parser {

  def parse(sentence: Sentence): Either[List[ParsingError], List[Expr]] = {
    EitherUtils.reduceList(sentence map parse)
  }

  def parse(token: Token): Either[ParsingError, Expr] = {
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
