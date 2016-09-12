package com.jhood.battlebot

trait MoveCalculator {
  def compute_play(myDirection: Direction,
                   myHand: List[Card],
                   myFlags: Map[Int, List[Card]],
                   opponentFlags: Map[Int, List[Card]],
                   claimedFlags: Map[Int, Direction]): PlayCardResponse
}
