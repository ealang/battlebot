package com.jhood.battlebot

object ResponseSerializer {
  def apply(msg: ResponseMessage): String = msg match {
    case NameSet(direction, name) => s"player ${direction.name} $name"
    case PlayCardResponse(flag, card)  => s"play ${flag} ${card.suite},${card.value}"
  }
}
