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

  test("tourWithStatusMessage") {

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

    val expected = for (planet <- stops) yield Event.Waypoint(planet.position)
    val expectedWithStatusEvent = expected ::: List(Event.StatusEvent("Welcome back home"))

    val mission = new Mission(solarSystem, venturer)
    val predicted = mission.events(Examples.tourWithStatusMessage)

    // check that the mission passes through one waypoint for each planet
    assertEquals(predicted, expectedWithStatusEvent)
  }

  test("tourWithMeasurement") {
    val expected = List(
      Event.Waypoint(mercury.position),
      Event.Waypoint(venus.position),
      Event.Waypoint(mars.position),
      Event.Measurement(solarSystem.gravityAt(mars.position)),
      Event.Waypoint(jupiter.position),
      Event.Waypoint(saturn.position),
      Event.Measurement(solarSystem.gravityAt(saturn.position)),
      Event.Waypoint(neptun.position),
      Event.Waypoint(uranus.position),
      Event.Measurement(solarSystem.gravityAt(uranus.position)),
      Event.Waypoint(pluto.position),
      Event.Waypoint(earth.position),
    )
    val expectedWithStatusEvent = expected ::: List(Event.StatusEvent("Welcome back home"))

    val mission = new Mission(solarSystem, venturer)
    val predicted = mission.events(Examples.tourWithMeasurement)

    // check that the mission passes through one waypoint for each planet
    assertEquals(predicted, expectedWithStatusEvent)
  }
}