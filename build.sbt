ThisBuild / scalaVersion := "3.4.1"

lazy val space = (project in file("."))
  .settings(
    name := "space",
    libraryDependencies ++= Seq(
      "io.monix" %% "minitest" % "2.9.6" % Test
    ),
    testFrameworks += new TestFramework("minitest.runner.Framework")
  )
