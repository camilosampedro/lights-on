package service

import com.google.inject.ImplementedBy
import org.eclipse.paho.client.mqttv3.{MqttClient, MqttMessage}
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

/**
  * PublishService
  */
object PublishService {
  def publish(message: String): Boolean = {
    Try(client.getTopic(topic)) match {
      case Success(messageTopic) =>
        val mqttMessage = new MqttMessage(message.getBytes("utf-8"))
        Try(messageTopic.publish(mqttMessage)) match {
          case Success(result) =>
            play.Logger.debug(s"Result of publishing: $result")
            true
          case Failure(exception) =>

            play.Logger.error(
              s"""
                 |An error occurred while trying to publish the message "$message" to $topic into $brokerUrl:
         """.stripMargin, exception)
            false
        }
      case Failure(exception) =>
        play.Logger.error(s"""
                             |An error occurred while trying to publish the message "$message" to $topic into $brokerUrl:
         """.stripMargin, exception)
        false
    }

  }
}
