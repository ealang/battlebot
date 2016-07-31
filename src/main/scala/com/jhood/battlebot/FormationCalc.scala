package com.jhood.battlebot

object FormationCalc {

  def score(cards: List[Card]): Int =
    battallion(cards).getOrElse(0)

  def battallion(cards: List[Card]): Option[Int] =
    cards match {
      case List(first,second,third) =>
        if(first.suite == second.suite && first.suite == third.suite)
          Some(cards.map(_.value).reduce(_ + _) + 300)
        else 
          None
      case _ => None
    }
}

