package mtl

import space.*

import scala.collection.immutable

object Interpreter {
  // Return an event
  def execute(task: Task, ship: SpaceShip, universe: Universe): Event = {
    task match {
      case Task.FlyTo(x, y) =>
        val positionVector = new Vector(x, y)
        ship.position = positionVector
        return Event.Waypoint(positionVector)
      case Task.MeasureGravity =>
        val currentPosition = ship.position
        val gravityAtPosition = universe.gravityAt(currentPosition)
        return Event.Measurement(gravityAtPosition)
      case Task.NavigateTo(planetName) =>
        val planet = universe.planet(planetName)
        planet match {
          case Some(value) =>
            var planetPosition = value.position
            ship.position = planetPosition
            return Event.Waypoint(planetPosition)
        }
      case Task.SetStatus(statusMessage) =>
        return Event.StatusEvent(statusMessage)
    }
  }
}
