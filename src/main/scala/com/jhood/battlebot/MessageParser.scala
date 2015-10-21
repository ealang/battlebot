package com.jhood.battlebot

import scala.util.parsing.combinator.RegexParsers

case class MessageParseException(msg: String) extends Exception(msg)

object MessageParser extends RegexParsers {

  def direction: Parser[Direction] = ("north" | "south") ^^ { s =>
    if(s.toString() == "north") North
    else South
  }

  def flagClaim: Parser[Option[Direction]] = ("unclaimed" | direction) ^^ {
    case d : Direction => Some(d)
    case "unclaimed" => None
  }

  def color: Parser[String] = """[a-z0-9]+""".r ^^ { _.toString }

  def value: Parser[Int] = """[0-9]+""".r ^^ { _.toInt }

  def card: Parser[Card] = (color ~ "," ~ value) ^^ {
    case color ~ "," ~ value => Card(color, value)
  }

  def nameRequest: Parser[GameMessage] = ("player" ~ direction ~ "name") ^^  {
    case _ ~ dir ~ _ => NameRequest(dir)
  }

  def colorDeclaration: Parser[GameMessage] = ("colors " ~ repN(6, color)) ^^ {
    case (_ ~ colors) => ColorsDeclaration(colors)
  }

  def playerHand: Parser[GameMessage] = ("player" ~ direction ~ "hand" ~ rep(card)) ^^ {
    case (_ ~ dir ~ _ ~ cards) => PlayerHand(dir, cards)
  }

  def claimStatus: Parser[GameMessage] = ("flag claim-status" ~ repN(9, flagClaim)) ^^ {
    case _ ~ claims => FlagClaimStatus(claims)
  }

  def flagCards: Parser[GameMessage] = ("flag" ~ value ~ "cards" ~ direction ~ rep(card)) ^^ {
    case _ ~ flag_index ~ _ ~ dir ~ cards => FlagCards(flag_index.toInt, dir, cards)
  }

  def opponentPlay: Parser[GameMessage] = ("opponent play" ~ value ~ card) ^^ {
    case _ ~ flag_index ~ played_card => OpponentPlay(flag_index, played_card)
  }

  def message: Parser[GameMessage] = (nameRequest | colorDeclaration | playerHand | claimStatus | flagCards | opponentPlay)

  def apply(input: String): GameMessage = parseAll(message, input) match {
    case Success(result, _) => result
    case x => throw MessageParseException(x.toString)
  }
}
