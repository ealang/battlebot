package com.jhood.battlebot

object FormationCalc {

  def score(cards: List[Card]): Int =
    wedge(cards)
      .orElse(phallanx(cards))
      .orElse(battalion(cards))
      .orElse(skirmish(cards))
      .getOrElse(sum(cards))

  def wedge(cards: List[Card]): Option[Int] = 
    for(
      skirmishValue <- skirmish(cards);
      battalionValue <- battalion(cards)
    ) yield sum(cards) + 500

  def phallanx(cards: List[Card]): Option[Int] =
    if(cards.length == 3 && cards.forall(_.value == cards.head.value))
      Some(sum(cards) + 400)
    else
      None 

  def battalion(cards: List[Card]): Option[Int] =
    cards match {
      case List(first,second,third) =>
        if(first.suite == second.suite && first.suite == third.suite)
          Some(sum(cards) + 300)
        else 
          None
      case _ => None
    }

  def skirmish(cards: List[Card]): Option[Int] =
    if(cards.length == 3) {
      val sortedCards = cards.map(_.value).sorted
      val expected = (sortedCards.head to (sortedCards.head + cards.size))
      if(sortedCards.zip(expected).forall(x => x._1 == x._2)) Some(sum(cards) + 200)
      else None
    } else {
      None
    }

  def sum(cards: List[Card]): Int =
    cards.map(_.value).fold(0)(_ + _)
}

