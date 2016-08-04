package com.jhood.battlebot

import scala.io.StdIn.readLine

object BattleBot {
  val strategy = new HighestScoreStrategy()
  val wrappedStrategy = new StdMsgStrategyWrapper(strategy)

  def main(args: Array[String]): Unit = {
    while(true) {
      val response = wrappedStrategy.update(readLine())
      response.map{ println(_) }
    }
  }
}
