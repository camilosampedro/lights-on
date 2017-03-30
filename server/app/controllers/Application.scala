package controllers

import javax.inject.Inject

import akka.actor.ActorSystem
import akka.stream.Materializer
import scala.concurrent.duration._
import controllers.socket.LightSocketActor
import model.Message
import play.api.libs.streams.ActorFlow
import play.api.mvc._
import shared.SharedMessages

class Application @Inject()(implicit system: ActorSystem, materializer: Materializer) extends Controller {



  def socket: WebSocket = WebSocket.accept[Message, Message] { _ =>
    ActorFlow.actorRef(out => LightSocketActor.props(out))
  }

  def index = Action {
    Ok(views.html.index(SharedMessages.itWorks))
  }

}
