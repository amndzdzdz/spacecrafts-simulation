package mtl

import space.*

object Interpreter {
  def execute(task: Task, ship: SpaceShip, universe: Universe): Event = {
    task match {
      case Task.FlyTo(x, y) =>
        return Event.Waypoint(new Vector(x, y))

      case Task.MeasureGravity =>
        val gravityAtPosition = universe.gravityAt(ship.position)
        return Event.Measurement(gravityAtPosition)

      case Task.NavigateTo(planetName) =>
        val planet = universe.planet(planetName)
        planet match {
          case Some(planet) =>
            ship.position = planet.position
            return Event.Waypoint(planet.position)
        }

      case Task.SetStatus(statusMessage) =>
        return Event.StatusEvent(statusMessage)
    }
  }
}
