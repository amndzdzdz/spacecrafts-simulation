package space

import mtl.Task.*
import mtl.PlanetName.*
import esl.*

@main // Run this to get a first result!
def empty() =
  Visualization.runProgram(
    Examples.venturer,
    Examples.solarSystem,
    Nil
  )

@main
def tour() =
  Visualization.runMission(
    Examples.venturer,
    Examples.solarSystem,
    Examples.tour
  )

@main
def tourWithCommands() =
  Visualization.runProgram(
    Examples.venturer,
    Examples.solarSystem,
    Examples.tourWithCommands
  )

object Examples {
  // https://www.theplanetstoday.com/
  val sun = Planet(0, 0, Sol, 64, 0xfff0c0)
  val mercury = Planet(-100, -80, Mercury, 8, 0xbcaaa4)
  val venus = Planet(120, -100, Venus, 16, 0xdce775)
  val earth = Planet(20, -180, Earth, 18, 0x1e88e5)
  val mars = Planet(-230, -40, Mars, 12, 0xf44336)
  val jupiter = Planet(30, 360, Jupyter, 42, 0xc1ac6c)
  val saturn = Planet(380, -20, Saturn, 32, 0xd2cc9e)
  val neptun = Planet(460, 60, Neptune, 24, 0x80deea)
  val uranus = Planet(420, 400, Uranus, 20, 0x64b5f6)
  val pluto = Planet(480, -400, Pluto, 6, 0x4db6ac)

  val venturer =
    SpaceShip(
      earth.position.x,
      earth.position.y,
      name = "Venturer",
      speed = 101,
      color = 0xba68c8
    )

  val tourWithCommands: List[Command] = List(
    Command.RotateTo(40.0),
    Command.Accelerate,
    Command.Wait(2.0),
    Command.Decelerate,
    Command.LogWayPoint,
    Command.CalibrateSensor,
    Command.Wait(3.0),
    Command.LogMeasurement,
    Command.RotateTo(0.0),
    Command.Accelerate,
    Command.Wait(2.0),
    Command.Decelerate,
    Command.LogWayPoint,
    Command.Display("Done")
    )

  val tour = List(
     NavigateTo(Mercury),
     NavigateTo(Venus),
     NavigateTo(Mars),
     NavigateTo(Jupyter),
     NavigateTo(Saturn),
     NavigateTo(Neptune),
     NavigateTo(Uranus),
     NavigateTo(Pluto),
     NavigateTo(Earth)
  )

  val tourWithStatusMessage = List(
    NavigateTo(Mercury),
    NavigateTo(Venus),
    NavigateTo(Mars),
    NavigateTo(Jupyter),
    NavigateTo(Saturn),
    NavigateTo(Neptune),
    NavigateTo(Uranus),
    NavigateTo(Pluto),
    NavigateTo(Earth),
    SetStatus("Welcome back home")
  )

  val tourWithMeasurement = List(
    NavigateTo(Mercury),
    NavigateTo(Venus),
    NavigateTo(Mars),
    MeasureGravity,
    NavigateTo(Jupyter),
    NavigateTo(Saturn),
    MeasureGravity,
    NavigateTo(Neptune),
    NavigateTo(Uranus),
    MeasureGravity,
    NavigateTo(Pluto),
    NavigateTo(Earth),
    SetStatus("Welcome back home")
  )

  val MTLvsESLTour = List(
    NavigateTo(Mercury),
    NavigateTo(Venus),
    NavigateTo(Mars),
    MeasureGravity,
    NavigateTo(Jupyter),
    NavigateTo(Saturn),
    MeasureGravity,
    NavigateTo(Neptune),
    NavigateTo(Uranus),
    MeasureGravity,
    NavigateTo(Pluto),
    NavigateTo(Earth)
  )

  val solarSystem = Universe(
    List(
      sun,
      mercury,
      venus,
      earth,
      mars,
      jupiter,
      saturn,
      neptun,
      uranus,
      pluto,
      venturer // put the ship last to be drawn over planets
    )
  )
}
