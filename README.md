# Lights On

This project uses a websocket server to listen to _"turn light"_ events and let anyone who is subscribed to the websocket to know when the light state has changed

## How to run
1. Clone this repository: `git clone https://github.com/camilosampedro/lights-on.git`
2. Go to the cloned location: `cd lights-on`
3. Run the server: `sbt run`

## Technologies

### Backend
 - __`Play Framework`:__ `2.5`
 - __`Scala`:__ `2.11.8`
 - __`Eclipse Paho`:__ `1.1.1`
 
### Frontend
 - __`ScalaJS`:__ `2.11.8`
 - __`JQuery`:__ `2.1.3`

Built with the template: [play-with-scalajs-example](https://github.com/vmunier/play-with-scalajs-example).

The lightbulb was taken from [Animated SVG Lightbulb](codepen.io/alexzaworski/pen/XJgmYv) codepen.

