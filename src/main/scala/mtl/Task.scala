package mtl

enum Task {
  case FlyTo(x: Int, y: Int)
  case MeasureGravity
  case NavigateTo(planetName: String)
  case SetStatus(statusMessage: String)
}