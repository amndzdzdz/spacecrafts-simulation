package space

import minitest.*
import space.*
import space.Examples.*

// click on the green arrow in the margin to run the tests
// <--
object Test extends SimpleTestSuite {
  test("tour") {

    val stops =
      List(
        mercury,
        venus,
        mars,
        jupiter,
        saturn,
        neptun,
        uranus,
        pluto,
        earth
      )

    val expected =
      for (planet <- stops)
        yield Event.Waypoint(planet.position)

    val mission = new Mission(solarSystem, venturer)
    val predicted = mission.events(Examples.tour)

    // check that the mission passes through one waypoint for each planet
    assertEquals(predicted, expected)
  }

  test("TODO") {
    // add more tests!
  }
}
