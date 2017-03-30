import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.persist.{MemoryPersistence, MqttDefaultFilePersistence}

/**
  * Created by camilosampedro on 29/03/17.
  */
package object service {
  val persistence = new MemoryPersistence // MqttDefaultFilePersistence("/tmp")
  val brokerUrl: String = "tcp://localhost:1883"
  val client = new MqttClient(brokerUrl, MqttClient.generateClientId(), persistence)
  val topic = "/homie/lucecilla/light"
}
