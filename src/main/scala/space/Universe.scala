package space

import esl.Controller

class Universe(var objects: List[SpaceObject], var time: Double = 0.0) {

  def planet(name: String): Option[Planet] = {
    objects.collectFirst {
      case planet: Planet if planet.name == name =>
        planet
    }
  }

  def timePassesTo(now: Double): Unit = {
    require(now >= time)

    // each object is moved and allowed to act
    for (obj <- objects)
      obj.timePasses(this, now)

    time = now
  }

  def gravityAt(here: Vector): Vector = {
    var gravity = Vector.zero

    for (case planet: Planet <- objects) {
      val diff = planet.position - here
      val pull =
        diff.norm * planet.mass
      val length = diff.length

      if (
        length > 8.0
      )
        gravity += pull / (length * length)
    }

    gravity * 10.0 // scale up to be measurable
  }
}

trait SpaceObject {
  def position: Vector
  def radius: Double
  def timePasses(universe: Universe, now: Double): Unit
}

class Planet(
    x: Double,
    y: Double,
    val name: String,
    val radius: Double,
    val color: Int
) extends SpaceObject {
  val position = Vector(x, y)
  def mass = radius * radius * radius
  def timePasses(universe: Universe, now: Double) = {}
}

class SpaceShip(
    x: Double,
    y: Double,
    val name: String,
    val speed: Double,
    val color: Int
) extends SpaceObject {
  var position = Vector(x, y)
  var velocity = Vector.zero
  var orientation = 0.0
  val radius = 40.0
  var error = Vector.zero
  var sensor = Vector.zero
  var controller = Controller(this) // the ships controller

  def timePasses(universe: Universe, now: Double) = {
    val dtime = now - universe.time
    position += velocity * dtime

    val vibration = Vector.random(velocity.length)
    error += vibration * 0.01

    val gravity = universe.gravityAt(position)
    if (gravity.length > 10000.0)
      sensor = Vector.zero
    else
      sensor = error + gravity * 0.1 + sensor * 0.9

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
