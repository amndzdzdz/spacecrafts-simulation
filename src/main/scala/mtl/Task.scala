package mtl

enum Task {
  case FlyTo(x: Int, y: Int)
  case MeasureGravity
  case NavigateTo(planetName: PlanetName)
  case SetStatus(statusMessage: String)
}