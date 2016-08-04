package com.jhood.battlebot

class DumbMoveCalculator extends MoveCalculator {
  def compute_flag(hand: List[Card],myFlags: Map[Int,List[Card]], opponentFlags:  Map[Int,List[Card]]): PlayCardResponse =
    PlayCardResponse(1, Card("color1",1))
}
