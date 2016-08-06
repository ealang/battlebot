package com.jhood.battlebot

class WeightedScoreCalculator extends MoveCalculator {

  val baseWeights: Map[Int,Int] = Map(
    1 -> 1, //
    2 -> 2, ////
    3 -> 4, ////////
    4 -> 3, //////
    5 -> 2, ////
    6 -> 3, //////
    7 -> 4, ////////
    8 -> 2, ////
    9 -> 1  //
  )

  def weights(myFlags: Map[Int, List[Card]]) = 
    baseWeights.map(orig => (orig._1, orig._2 + myFlags.getOrElse(orig._1, List()).size))

  def compute_play(
                    hand: List[Card],
                    myFlags: Map[Int, List[Card]],
                    opponentFlags: Map[Int, List[Card]],
                    claimedFlags: Map[Int, Direction])
  : PlayCardResponse = bestPlayOnAllFlags(myFlags, hand) match {
    case Some((flag, cards)) => PlayCardResponse(flag, cards.filter(!myFlags(flag).contains(_)).head)
    case None => PlayCardResponse(1, Card("color1",1))
  }

  def bestPlayOnAllFlags(myFlags: Map[Int, List[Card]], hand: List[Card]): Option[(Int, List[Card])] =
    myFlags.mapValues(bestMoveOnFlag(_,hand))         // Get the best move on each flag
      .filter(_._2.isDefined).mapValues(_.get)        // Filter out undefined moves
      .map(orig => (orig._1,(orig._2._1 * weights(myFlags)(orig._1), orig._2._2)))
      .toList.sortWith(_._2._1 > _._2._1)             // Sort to get the highest score
      .headOption.map(orig => (orig._1,orig._2._2))   // Only return the flag number and cards needed for play


  def bestMoveOnFlag(flagCards: List[Card], hand: List[Card]): Option[(Int,List[Card])] =
    if(flagCards.size >= 3) None
    else hand.toSet.subsets(3 - flagCards.size)                 // Select all hand combinations to complete the flag
      .map(handCombo => (handCombo ++ flagCards.toSet).toList)  // Compute all combinations of flag plays
      .map(play => (FormationCalc.score(play), play))           // Compute the score of each flag play
      .toList.sortWith(_._1 > _._1).headOption                  // Take the highest score
}
