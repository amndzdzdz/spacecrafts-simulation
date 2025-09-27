package space

import mtl.Interpreter.execute
import mtl.*

enum Event {
  case Waypoint(position: Vector)
  case Measurement(gravity: Vector)
  case StatusEvent(statusMessage: String)
}

class Mission(universe: Universe, ship: SpaceShip) {
  def events(tasks: List[Task]): List[Event] = {
    val events = for (task <- tasks) yield execute(task, ship, universe)
    return events
  }
}
