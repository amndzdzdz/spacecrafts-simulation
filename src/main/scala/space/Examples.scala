package space

import mtl.Task.*

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
def science() =
  Visualization.runMission(
    Examples.venturer,
    Examples.solarSystem,
    Examples.science // TODO: see below!
  )

object Examples {
  // https://www.theplanetstoday.com/
  val sun = Planet(0, 0, "Sol", 64, 0xfff0c0)
  val mercury = Planet(-100, -80, "Mercury", 8, 0xbcaaa4)
  val venus = Planet(120, -100, "Venus", 16, 0xdce775)
  val earth = Planet(20, -180, "Earth", 18, 0x1e88e5)
  val mars = Planet(-230, -40, "Mars", 12, 0xf44336)
  val jupiter = Planet(30, 360, "Jupiter", 42, 0xc1ac6c)
  val saturn = Planet(380, -20, "Saturn", 32, 0xd2cc9e)
  val neptun = Planet(460, 60, "Neptun", 24, 0x80deea)
  val uranus = Planet(420, 400, "Uranus", 20, 0x64b5f6)
  val pluto = Planet(480, -400, "Pluto", 6, 0x4db6ac)

  val venturer =
    SpaceShip(
      earth.position.x,
      earth.position.y,
      name = "Venturer",
      speed = 101,
      color = 0xba68c8
    )

  // TODO: once you have implemented the abstract syntax,
  //       you can uncomment the following missions and try them out via the main functions above

  val tour = List(
     NavigateTo("Mercury"),
     NavigateTo("Venus"),
     NavigateTo("Mars"),
     NavigateTo("Jupiter"),
     NavigateTo("Saturn"),
     NavigateTo("Neptun"),
     NavigateTo("Uranus"),
     NavigateTo("Pluto"),
     NavigateTo("Earth"),
  )

  val science = List()

  // val science = List(
  //   FlyTo(-60, 60),
  //   MeasureGravity,
  //   FlyTo(-500, -400),
  //   MeasureGravity,
  //   FlyTo(30, 450),
  //   MeasureGravity,
  //   FlyTo(300, 300),
  //   MeasureGravity,
  //   FlyTo(560, -80),
  //   MeasureGravity,
  //   NavigateTo("Pluto"),
  //   MeasureGravity,
  //   NavigateTo("Earth"),
  //   SetStatus("That was fun")
  // )

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
