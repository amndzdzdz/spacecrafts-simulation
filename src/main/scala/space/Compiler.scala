package space

import mtl.*
import esl.*
import esl.Command.*

class Compiler(universe: Universe, ship: SpaceShip) {
  def compile(tasks: List[Task]): List[Command] = {
    (for (task <- tasks) yield this.translate(task)).flatten
  }

  private def translate(task: Task): List[Command] = {
    task match {
      case Task.FlyTo(x, y) =>
        val fromVector = this.ship.position
        val toVector = new Vector(x, y)
        this.emitFlight(fromVector, toVector)

      case Task.MeasureGravity =>
        this.emitMeasurement()

      case Task.NavigateTo(planetName) =>
        val fromVector = this.ship.position
        val planet = this.universe.planet(planetName)

        planet match {
          case Some(value) =>
            this.emitFlight(fromVector, value.position)
          case None =>
            return Nil
        }

      case Task.SetStatus(statusMessage) =>
        this.emitStatus(statusMessage)
    }
  }

  private def emitFlight(from: Vector, to: Vector): List[Command] = {
    val diff = to - from
    val angle = diff.angle
    val length = diff.length
    val time = length / ship.speed
    this.ship.position = to

    val commands = List(
      RotateTo(angle),
      Accelerate,
      Wait(time),
      Decelerate,
      LogWayPoint
    )
    return commands
  }

  private def emitMeasurement(): List[Command] = {
    val commands = List(
      CalibrateSensor,
      Wait(4.0),
      LogMeasurement
    )
    return commands
  }

  private def emitStatus(statusMessage: String): List[Command] = {
    List(Display(statusMessage))
  }

  //optimize a sequence of commands by removing unnecessary deceleration
  def optimize(commands: List[Command]): List[Command] = {
    commands match {
      case Nil =>
        Nil

      case Command.Decelerate :: rest if accelerates(rest) =>
        optimize(rest)

      case first :: rest =>
        first :: optimize(rest)
    }
  }

  def accelerates(commands: List[Command]): Boolean = {
    commands match {
      case Command.Accelerate :: _ =>
        true

      case Command.Display(status) :: rest =>
        accelerates(rest)

      case Command.RotateTo(angle) :: rest =>
        accelerates(rest)

      case Command.LogWayPoint :: rest =>
        accelerates(rest)

      case _ =>
        false
    }
  }
}