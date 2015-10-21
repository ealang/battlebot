package com.jhood.battlebot

import scala.concurrent.Await
import scala.concurrent.duration._

class StdMsgStrategyWrapper(wraps: Strategy) {
  def update(msg: String): Option[String] = {
    try {
      val parsed_request = MessageParser(msg)
      val response = Await.result(wraps.update(parsed_request), 9 seconds)
      response match {
        case Some(response_msg) => Some(ResponseSerializer(response_msg))
        case None => None
      }

    } catch {
      case MessageParseException(ex_msg) =>
        System.err.println(s"Exception Parsing Message: $msg")
        None
    }
  }
}
