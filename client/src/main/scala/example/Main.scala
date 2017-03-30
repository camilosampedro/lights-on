package example

import org.scalajs.dom
import org.scalajs.dom.{Event, MessageEvent, WebSocket}
import shared.SharedMessages

import scala.scalajs.js

object Main extends js.JSApp {
  def main(): Unit = {
    dom.document.getElementById("scalajsShoutOut").textContent = SharedMessages.itWorks
    val webSocket = new WebSocket("ws://localhost:9000/socket")
    webSocket.onopen = (_: Event) => {
      println("Sending message...")
      webSocket.send(
        """
          |{
          | "information": "true"
          |}
        """.stripMargin
      )
      println("Message sent")
    }

    webSocket.onmessage = (event: MessageEvent) => println(s"Received ${event.data}")

  }
}
