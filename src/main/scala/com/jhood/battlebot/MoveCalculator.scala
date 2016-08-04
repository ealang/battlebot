package com.jhood.battlebot

trait MoveCalculator {
  def compute_flag(hand: List[Card],myFlags: Map[Int,List[Card]], opponentFlags:  Map[Int,List[Card]]): PlayCardResponse
}
