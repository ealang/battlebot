package com.jhood

package object battlebot {
  object South extends Direction("south")
  object North extends Direction("north")
  sealed abstract class Direction(val name: String)

  case class Card(suite: String, value: Int)

  case class OpponentPlay(flag: Int, card: Card) extends GameMessage
  case class FlagCards(flag: Int, direction: Direction, cards: List[Card]) extends GameMessage
  case class FlagClaimStatus(status: List[Option[Direction]]) extends GameMessage
  case class PlayerHand(direction: Direction, cards: List[Card]) extends GameMessage
  case class ColorsDeclaration(colors: List[String]) extends GameMessage
  case class NameRequest(direction: Direction) extends GameMessage
  sealed abstract class GameMessage()
}
