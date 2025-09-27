package space

import minitest.*
import space.*

// click on the green arrow in the margin to run the tests
// <--
object Test extends SimpleTestSuite {
  test("tour") {

    val stops =
      List(
        Examples.mercury,
        Examples.venus,
        Examples.mars,
        Examples.jupiter,
        Examples.saturn,
        Examples.neptun,
        Examples.uranus,
        Examples.pluto,
        Examples.earth
      )

    val expected =
      for (planet <- stops)
        yield Event.Waypoint(planet.position)

    val mission = new Mission(Examples.solarSystem, Examples.venturer)
    val predicted = mission.events(Examples.tour)

    // check that the mission passes through one waypoint for each planet
    assertEquals(predicted, expected)
  }

  test("TODO") {
    // add more tests!
  }
}
