import sbt._

object Version {
  final val Scala = "2.13.1"
  final val ScalaTest = "3.1.0"
  final val PureConfig = "0.12.2"
}

object Library {
  val PureConfig = "com.github.pureconfig" %% "pureconfig" % Version.PureConfig
  val ScalaTest  = "org.scalatest"         %% "scalatest"  % Version.ScalaTest  % "test"
}
