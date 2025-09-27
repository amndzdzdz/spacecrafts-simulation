package space

import esl.Controller

// class to represent the universe via its objects and current time
class Universe(var objects: List[SpaceObject], var time: Double = 0.0) {

  // utility to look up a named planet
  def planet(name: String): Option[Planet] = {
    objects.collectFirst {
      case planet: Planet if planet.name == name =>
        planet
    }
  }

  // moves the universe forward in time in seconds to "now"
  def timePassesTo(now: Double): Unit = {
    require(now >= time)

    // each object is moved and allowed to act
    for (obj <- objects)
      obj.timePasses(this, now)

    time = now
  }

  // calculates the gravitational pull from the planets at some point in the universe
  def gravityAt(here: Vector): Vector = {
    var gravity = Vector.zero

    for (case planet: Planet <- objects) {
      val diff = planet.position - here
      val pull =
        diff.norm * planet.mass // pull is proportional to mass in the correct direction
      val length = diff.length

      if (
        length > 8.0
      ) // don't add if too close to avoid huge pul or even division by zero
        gravity += pull / (length * length)
    }

    gravity * 10.0 // scale up to be measurable
  }
}

// base type for space objects
trait SpaceObject {
  def position: Vector
  def radius: Double
  def timePasses(universe: Universe, now: Double): Unit
}

// planets are initialized at some position with some properties
class Planet(
    x: Double,
    y: Double,
    val name: String,
    val radius: Double,
    val color: Int
) extends SpaceObject {
  val position = Vector(x, y) // fixed
  def mass = radius * radius * radius // mass of a sphere is cubic in its radius
  def timePasses(universe: Universe, now: Double) = {}
}

// space ships are initialized at some position with some properties
class SpaceShip(
    x: Double,
    y: Double,
    val name: String,
    val speed: Double,
    val color: Int
) extends SpaceObject {
  var position = Vector(x, y) // initial position
  var velocity = Vector.zero // initial velocity

  var orientation = 0.0 // initial orientation is to the right
  val radius = 40.0 // default size of ships

  var error = Vector.zero // accumulated error of the gravity sensor
  var sensor = Vector.zero // measured gravity, disturbed by flight

  var controller = Controller(this) // the ships controller

  def timePasses(universe: Universe, now: Double) = {
    // move the spaceship according to Newton's law of motion
    // note: gravity does not affect this motion for simplicity
    val dtime = now - universe.time
    position += velocity * dtime

    // random disturbance of the gravity sensor
    val vibration = Vector.random(velocity.length)
    error += vibration * 0.01

    // adjust the seonsor
    val gravity = universe.gravityAt(position)
    if (gravity.length > 10000.0)
      sensor = Vector.zero
    else
      sensor = error + gravity * 0.1 + sensor * 0.9

    // run the controller
    controller.execute(now)
  }

  def calibrate() = {
    error = Vector.zero
    println(name + ": calibrating sensor")
  }

  def accelerate() = {
    velocity = Vector.polar(speed, orientation)
    println(name + ": accelerated to speed " + velocity)
  }

  def decelerate() = {
    velocity = Vector.zero
    println(name + ": decelerated to speed " + velocity)
  }

  def rotateTo(angle: Double) = {
    orientation = angle
    println(name + ": rotated to " + orientation)
  }
}
