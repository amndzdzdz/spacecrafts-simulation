package space.domainModel

class Controller(val ship: SpaceShip) {
  // sequence of commands to be executed,
  // once they have been processed,
  // they need to be removed from this list
  var commands: List[Command] = Nil

  // flight log produced including waypoint and measurement events
  var flightlog: List[Event] = Nil

  // if waitUntil is larger than the current time:
  // we need to wait until then
  var waitUntil: Double = 0.0

  var status: String = "idle"

  // loads a program into the controller
  // by appending at the end of the existing command list
  def program(commands: List[Command]) = {
    this.commands ++= commands
  }

  // called by the simulation periodically,
  // where parameter now gives the current time (in seconds),
  // that is guaranteed to increase monotonically between calls
  def execute(now: Double): Unit = {
    if (commands.nonEmpty && now >= waitUntil) {
      // TODO: execute a few commands from the command list
      // repeat: remove and look at the first command
      // - for a wait command, set waitUntil to now and return
      // - for ship control commands, dispatch them via the ship referenced by the controller
      //   (ship.accelerate, ship.decelerate, ship.rotateTo, ship.calibrate in Universe.scala)
      // - for log commands, create the corresponding events from ship.position resp. ship.sensor (see enum Event in Mission.scala)
      // - for a display command set the status attribute, it is read by the visualization

      ???
    }
  }
}
