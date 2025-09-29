package esl

import space.Event.Measurement
import space.{Event, SpaceShip}

class Controller(val ship: SpaceShip) {
  var commands: List[Command] = Nil
  var flightlog: List[Event] = Nil
  var waitUntil: Double = 0.0
  var status: String = "idle"

  def program(commands: List[Command]) = {
    this.commands ++= commands
  }

  def execute(now: Double): Unit = {
    if (commands.nonEmpty && now >= waitUntil) {
      val command = this.commands.head
      this.commands = this.commands.tail

      command match {
        case Command.RotateTo(angle) =>
          this.ship.rotateTo(angle)

        case Command.Accelerate =>
          this.ship.accelerate()

        case Command.Wait(time) =>
          this.waitUntil = now + time

        case Command.Decelerate =>
          this.ship.decelerate()
          this.waitUntil = now + 1.0

        case Command.LogWayPoint =>
          this.flightlog = this.flightlog :+ Event.Waypoint(this.ship.position)

        case Command.CalibrateSensor =>
          this.ship.calibrate()

        case Command.LogMeasurement =>
          this.flightlog = this.flightlog :+ Measurement(this.ship.sensor)

        case Command.Display(text) => 
          this.status = text
      }
    }
  }
}
