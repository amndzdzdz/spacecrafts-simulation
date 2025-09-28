package esl

enum Command {
  // This part of the AST corresponds to the task FlyTo and NavigateTo of the MTL
  case RotateTo()
  case Accelerate
  case Wait(time: Double)
  case Decelerate
  case LogWayPoint
  // This part of the AST corresponds to the task MeasureGravity of the MTL
  case CalibrateSensor
  case LogMeasurement
  // This part of the AST corresponds to the task SetStatus of the MTL
  case Display(text: String)
}