package com.jhood.battlebot

import scala.concurrent.Future

trait Strategy {
  def update(msg: GameMessage): Future[Option[ResponseMessage]]
}
