package esl

enum Command {
  case RotateTo(angle: Double)
  case Accelerate
  case Wait(time: Double)
  case Decelerate
  case LogWayPoint
  case CalibrateSensor
  case LogMeasurement
  case Display(text: String)
}