val scalatest = "org.scalatest" %% "scalatest" % "3.2.19" % "test"

ThisBuild / scalaVersion := "3.4.2"
ThisBuild / version := "0.1-SNAPSHOT"
ThisBuild / javacOptions += "-Xlint:deprecation"
ThisBuild / scalacOptions += "-deprecation"

lazy val root = (project in file("."))
    .settings(
        name := "Scala Java Enum",
        libraryDependencies ++= Seq(
            scalatest
        )
    )

