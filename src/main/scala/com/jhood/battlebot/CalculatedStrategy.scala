
package com.jhood.battlebot

import scala.concurrent.Future
import scala.collection.mutable.Map

class CalculatedStrategy(calc: MoveCalculator) extends Strategy {
  var myDirection: Direction = North

  var myFlags: Map[Int,List[Card]] = Map(
    1 -> List(), 2 -> List(), 3 -> List(), 4 -> List(),
    5 -> List(), 6 -> List(), 7 -> List(), 8 -> List(),
    9 -> List()
  )

  var opponentFlags: Map[Int,List[Card]] = Map(
    1 -> List(), 2 -> List(), 3 -> List(), 4 -> List(),
    5 -> List(), 6 -> List(), 7 -> List(), 8 -> List(),
    9 -> List()
  )

  var claimedFlags: Map[Int,Direction] = Map()

  var hand: List[Card] = List()
 
  def update(msg: GameMessage): Future[Option[ResponseMessage]] = Future.successful(
    msg match {
      case NameRequest(direction) => 
        myDirection = direction
        Some(NameSet(direction, "darthbagel"))
      case FlagCards(flag,direction,cards) =>
        if(direction == myDirection) myFlags += ((flag,cards))
        else opponentFlags += ((flag,cards))
        None
      case PlayerHand(dir,cards) => 
        hand = cards
        None
      case FlagClaimStatus(status) =>
        status.zip(1 to 10).foreach {
          case (Some(dir),flag) => claimedFlags += ((flag,dir))
          case _ =>
        }
        None
      case PlayCard() => 
        Some(calc.compute_flag(hand,myFlags.toMap,opponentFlags.toMap))
      case _ => 
        None
    }
  )
}
