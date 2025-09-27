package space

import java.awt.{BasicStroke, Color, Graphics, Graphics2D}
import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.*
import mtl.*
import esl.*

// two entry points into the visualization,
// one for running a mission that gets compiled to commands
// and one for running commands directly
object Visualization {
  def runMission(
      ship: SpaceShip,
      universe: Universe,
      mission: List[Task]
  ): Unit = {
    val compiler = Compiler(universe, ship)
    val program = compiler.compile(mission)
    val program_ = compiler.optimize(program)
    runProgram(ship, universe, program_)
  }

  def runProgram(
      ship: SpaceShip,
      universe: Universe,
      program: List[Command]
  ): Unit = {
    ship.controller.program(program)

    val visualization = Visualization(universe)
    val frame = JFrame("2D Space Animation")
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    frame.setSize(1280, 900)
    frame.add(visualization)
    frame.setVisible(true)

    visualization.start()
  }
}

class Visualization(universe: Universe) extends JPanel with ActionListener {
  def start() = {
    // initialize a timer that ticks every 32 milliseconds and calls actionPerformed
    universe.time = now()
    val timer = new Timer(32, this)
    timer.start()
  }

  def now(): Double = {
    System.currentTimeMillis() / 1000.0
  }

  def actionPerformed(e: ActionEvent): Unit = {
    // get current time and run the usiverse
    universe.timePassesTo(now())
    // make sure graphics are updated then
    repaint()
  }

  override def paintComponent(g: Graphics) = {
    super.paintComponent(g)
    setBackground(Color.black)
    for (obj <- universe.objects)
      paint(g, obj)
  }

  def paint(g1: Graphics, obj: SpaceObject) = {
    val g = g1.asInstanceOf[Graphics2D]

    val cx = getWidth() / 2
    val cy = getHeight() / 2

    obj match {
      case planet: Planet =>
        import planet.{color, position, radius}

        val x = position.x - radius
        val y = position.y + radius
        val w = radius * 2
        val h = radius * 2

        g.setColor(new Color(color))
        g.fillOval(cx + x.toInt, cy - y.toInt, w.toInt, h.toInt)

        val px = position.x + planet.radius
        val py = position.y - planet.radius

        val text = planet.name + " (mass: " + planet.mass.toInt + ")"
        g.drawString(text, cx + px.toInt, cy - py.toInt)

      case ship: SpaceShip =>
        import ship.{color, orientation, position}

        val size = ship.radius

        val x0 = position.x
        val y0 = position.y

        val x1 = (position.x + size * Math.cos(orientation))
        val y1 = (position.y + size * Math.sin(orientation))

        val x2 =
          (position.x + size * Math.cos(orientation + 2 * Math.PI / 3) / 2)
        val y2 =
          (position.y + size * Math.sin(orientation + 2 * Math.PI / 3) / 2)
        val x3 =
          (position.x + size * Math.cos(orientation + 4 * Math.PI / 3) / 2)
        val y3 =
          (position.y + size * Math.sin(orientation + 4 * Math.PI / 3) / 2)

        val xs = Array(cx + x1.toInt, cx + x2.toInt, cx + x3.toInt)
        val ys = Array(cy - y1.toInt, cy - y2.toInt, cy - y3.toInt)

        g.setColor(new Color(color))
        g.fillPolygon(xs, ys, 3)

        val px = position.x + ship.radius
        val py = position.y - ship.radius

        val text = ship.name + " (" + ship.controller.status + ")"
        g.drawString(text, cx + px.toInt, cy - py.toInt)

        g.setStroke(new BasicStroke(3));
        g.setColor(new Color(0x00ff00))
        val gx = x0 + ship.sensor.x
        val gy = y0 + ship.sensor.y

        g.drawLine(
          cx + x0.toInt,
          cy - y0.toInt,
          cx + gx.toInt,
          cy - gy.toInt
        )
    }
  }
}
