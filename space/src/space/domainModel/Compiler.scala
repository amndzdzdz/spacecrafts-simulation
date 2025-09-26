package space.domainModel

class Compiler(universe: Universe, ship: SpaceShip) {
  def compile(tasks: List[Task]): List[Command] = {
    // TODO: compile list of tasks into list of commands,
    //       you may use optional helper methods including those below
    ???
  }

  def emitFlight(from: Vector, to: Vector): List[Command] = {
    val diff = to - from
    val angle = diff.angle
    val length = diff.length
    val time = length / ship.speed

    // TODO: return sequence of commands to fly between points from and to:
    // - rotate ship into the direction of angle
    // - accelerate the ship
    // - wait for the calculated amount of time
    // - decelerate the ship
    ???
  }

  def emitMeasurement(): List[Command] = {
    // TODO: return sequence of commands to conduct a measurement
    // - calibrate sensor
    // - wait for some time (e.g. 4 seconds)
    // - take measurement
    ???
  }

  def optimize(commands: List[Command]): List[Command] = {
    // TODO: optimize sequence of commands by removing unnecessary deceleration
    //       for now implemented as a non-operation, which is safe
    commands
  }
}
