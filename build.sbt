name := "spacecrafts"

version := "0.1"

scalaVersion := "3.4.1"

// use the %%% operator for Scala.js
libraryDependencies += "io.monix" %% "minitest" % "2.9.6" % "test"

testFrameworks += new TestFramework("minitest.runner.Framework")