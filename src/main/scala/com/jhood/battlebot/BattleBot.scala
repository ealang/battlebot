package com.jhood.battlebot

import scala.io.StdIn.readLine

object BattleBot {
  def main(args: Array[String]): Unit = {
    val wrappedStrategy = new StdMsgStrategyWrapper(strategy(args))

    while(true) {
      val response = wrappedStrategy.update(readLine())
      response.map{ println(_) }
    }
  }

  def strategy(args: Array[String]): Strategy = args match {
    case Array("dumb") =>
      System.err.println("Chose dumb strategy") 
      new RandomStrategy
    case Array("highest") => 
      System.err.println("Chose highest score strategy")
      new HighestScoreStrategy
    case Array("weighted") =>
      System.err.println("Chose weighted score strategy")
      new WeightedScoreStrategy
    case _ => 
      System.err.println("Chose default strategy")
      new WeightedScoreStrategy
  }
}
