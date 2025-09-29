package space

import scala.util.Random

case class Vector(x: Double, y: Double) {
  def *(that: Double) =
    Vector(this.x * that, this.y * that)

  def /(that: Double) =
    Vector(this.x / that, this.y / that)

  infix def dot(that: Vector) =
    this.x * that.x + this.y * that.y

  def +(that: Vector) =
    Vector(this.x + that.x, this.y + that.y)

  def -(that: Vector) =
    Vector(this.x - that.x, this.y - that.y)

  def length =
    Math.sqrt(x * x + y * y)

  def norm =
    this / length

  def angle =
    Math.atan2(y, x)
}

object Vector {
  val zero = Vector(0, 0)

  def polar(length: Double, angle: Double) =
    Vector(length * Math.cos(angle), length * Math.sin(angle))

  def random(length: Double) =
    Vector.polar(length, 2.0 * Math.PI * Random.nextDouble())
}
