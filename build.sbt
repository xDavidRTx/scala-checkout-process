import sbt._

name := "triggerise-be"

scalaVersion := Version.Scala

libraryDependencies ++= Seq(
  Library.ScalaTest,
  Library.PureConfig
)