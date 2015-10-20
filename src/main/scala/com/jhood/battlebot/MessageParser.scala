package com.jhood.battlebot

import scala.util.parsing.combinator.RegexParsers

object MessageParser extends RegexParsers {

  implicit class ExtendedLists[T](l: List[T]) {
    def toCards: List[Card] = l.map{
      case color ~ "," ~ value => Card(color.toString(), value.toString().toInt)
    }
  }

  implicit class ExtendedStrings(s: String) {
    def toDirection: Direction = if(s == "north") North else South
    def toOptionalDirection: Option[Direction] =
      if(s == "unclaimed") None
      else Some(toDirection)
  }

  def direction = "north" | "south"

  def color = """[a-z0-9]+""".r

  def value = """[0-9]+""".r

  def card = color ~ "," ~ value

  def flag_claim = "unclaimed" | direction

  def name_request: Parser[GameMessage] = "player" ~ direction ~ "name" ^^  {
    case _ ~ dir ~ _ => NameRequest(dir.toDirection)
  }

  def colors_declaration: Parser[GameMessage] = "colors " ~ repN(6, color) ^^ {
    case (_ ~ colors) => ColorsDeclaration(colors)
  }

  def player_hand: Parser[GameMessage] = "player" ~ direction ~ "hand" ~ rep(card) ^^ {
    case (_ ~ dir ~ _ ~ cards) => PlayerHand(dir.toDirection, cards.toCards)
  }

  def claim_status: Parser[GameMessage] = "flag claim-status" ~ repN(9, flag_claim) ^^ {
    case _ ~ claims => FlagClaimStatus(claims.map(_.toOptionalDirection))
  }

  def flag_cards: Parser[GameMessage] = "flag" ~ value ~ "cards" ~ direction ~ rep(card) ^^ {
    case _ ~ flag_index ~ _ ~ dir ~ cards => FlagCards(flag_index.toInt, dir.toDirection, cards.toCards)
  }

  def message: Parser[GameMessage] = name_request | colors_declaration | player_hand | claim_status | flag_cards

  def apply(input: String): Option[GameMessage] = parseAll(message, input) match {
    case Success(result, _) => Some(result)
    case x => println(x); None
  }
}
