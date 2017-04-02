package controllers

import javax.inject.Inject

import akka.actor.ActorSystem
import akka.stream.Materializer

import controllers.socket.LightSocketActor
import model.Message
import play.api.libs.streams.ActorFlow
import play.api.mvc._
import service.SubscribeService
import shared.SharedMessages

class Application @Inject()(implicit system: ActorSystem, materializer: Materializer) extends Controller {
  SubscribeService.subscribe()
  def socket: WebSocket = WebSocket.accept[Message, Message] { _ =>
    ActorFlow.actorRef(out => LightSocketActor.props(out))
  }

  def index = Action {
    Ok(views.html.index(SharedMessages.itWorks))
  }

}
