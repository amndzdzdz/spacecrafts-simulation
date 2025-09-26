package space.domainModel

enum Event {
  case Waypoint(position: Vector)
  case Measurement(gravity: Vector)
}

class Mission(universe: Universe, ship: SpaceShip) {
  def events(tasks: List[Task]): List[Event] = {
    // TODO: predict the outcome of the mission as a sequence of events
    // there is no need to use the controller or simulation here
    ???
  }
}
