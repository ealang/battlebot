package com.jhood.battlebot

class DumbMoveCalculator extends MoveCalculator {
  def compute_play(hand: List[Card],myFlags: Map[Int,List[Card]], opponentFlags:  Map[Int,List[Card]], claimedFlags: Map[Int,Direction]): PlayCardResponse =
    PlayCardResponse(1, Card("color1",1))
}
