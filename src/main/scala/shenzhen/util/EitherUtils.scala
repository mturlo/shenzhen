package shenzhen.util

trait EitherUtils {

  def reduceList[L, R](input: List[Either[L, R]]): Either[List[L], List[R]] = {
    input.partition(_.isLeft) match {
      case (Nil, rights) => Right(for (Right(i) <- rights) yield i)
      case (lefts, _)    => Left(for (Left(s) <- lefts) yield s)
    }
  }

}

object EitherUtils extends EitherUtils
