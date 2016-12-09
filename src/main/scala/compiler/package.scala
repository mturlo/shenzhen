

package object compiler {

  type Token = String

  type Sentence = List[Token]

  case class LexicalError(token: Token)

  case class ParsingError(token: Token)

}
