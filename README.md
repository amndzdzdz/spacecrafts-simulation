# Awesome Spacecrafts

## Given Code

- `Universe.scala` contains the definition of the universe,
  its physics, as well as space objects (planetes and ships)
- `Visualization.scala` contains a nice graphical view of the universe
- `Examples.scala` contains a few main functions, which you can use as entry points

You can just run `@main def empty()` in `Examples.scala` to see how it looks like!

## Implementation Tasks

- Mission Task Language: `Task.scala` and event prediction in `Mission.scala`
- Control Language `Command.scala` and its interpreter `Controller.scala`
- Compiler from MTL to control programs in `Compiler.scala`
- Running via `Examples.scala` and tests in `Test.scala`

## Recommended IDEs

- VS Codium (https://vscodium.com/) with Metals plugin (https://scalameta.org/metals/)
- IntelliJ with the Scala plugin (https://www.jetbrains.com/help/idea/get-started-with-scala.html)

The build script `build.sc` is written in Mill (https://github.com/com-lihaoyi/mill)
but you should need to work with it manually and you can set up the project by hand in you IDE if you want.
The only dependency is MiniTest for Scala (https://github.com/monix/minitest).