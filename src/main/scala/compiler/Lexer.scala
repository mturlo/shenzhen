package compiler

trait Lexer {

  val tokenRegex = "[#:\\w]*"

  def tokenize(input: String): Either[List[LexicalError], List[Sentence]] = {

    val lines = input.trim.split("\n").toList

    val sentences = lines
      .filterNot(_.isEmpty)
      .map(_.trim.split(" ").toList)

    val badTokens = sentences
      .flatten
      .filter(!_.matches(tokenRegex))

    badTokens match {
      case Nil =>
        Right(sentences)
      case someTokens =>
        Left(someTokens map LexicalError)
    }

  }

}
