package com.jhood.battlebot

import scala.concurrent.Future

class RandomStrategy extends Strategy {
  def update(msg: GameMessage): Future[Option[ResponseMessage]] = Future.successful(
    msg match {
      case NameRequest(direction) => Some(NameSet(direction, "RandomStrategy"))
      case PlayCard() => Some(PlayCardResponse(1, Card("color1",1)))
      case _ => None
    }
  )
}
