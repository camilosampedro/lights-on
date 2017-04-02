package example

import org.scalajs.dom.{Event, MessageEvent, WebSocket}
import org.scalajs.jquery.jQuery
import prickle._

import scala.scalajs.js
import scala.util.{Failure, Success}

object Main extends js.JSApp {
  val webSocket = new WebSocket("ws://localhost:9000/socket")

  var on = false

  def sendMessage(message: String): Unit = {
    webSocket.send(Pickle.intoString(Information(message)))
  }

  def powerClicked: () => Unit = () => {
    //jQuery("body").toggleClass("active")
    sendMessage((!on).toString)
  }

  def main(): Unit = {
    // dom.document.getElementById("scalajsShoutOut").textContent = SharedMessages.itWorks

    webSocket.onopen = (_: Event) => {
      println("Sending message...")
      jQuery(".power").click(powerClicked)

      sendMessage("true")

      println("Message sent")
    }

    webSocket.onmessage = (event: MessageEvent) => {
      println(s"Received ${event.data}")
      Unpickle[Information].fromString(event.data.toString) match {
        case Success(information) =>
          information.information match {
            case "true" =>
              on = true
              jQuery("body").addClass("active")
            case "false" =>
              on = false
              jQuery("body").removeClass("active")
            case another => println(s"Message not expected: $another")
          }
        case Failure(error) => println(s"An error occured while trying to parse the message: $error")
      }
    }

  }
}
