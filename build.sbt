name := "zk4s"
organization := "it.softfork"
version := "0.1.0"

scalaVersion in ThisBuild := "2.13.2"

scalacOptions := Seq(
  "-unchecked",
  "-feature",
  "-deprecation",
  "-Xsource:3",
  "-Ywarn-dead-code",
  "-Ywarn-extra-implicit",
  "-Ywarn-extra-implicit",
  "-Ywarn-unused:locals",
  "-Ywarn-unused:patvars",
  "-Ywarn-unused:privates",
  "-Ywarn-unused:imports",
  "-Ymacro-annotations"
)

resolvers += Resolver.bintrayRepo("consensys", "pegasys-repo")

libraryDependencies ++= Seq(
  "tech.pegasys.teku.internal" % "bls" % "0.12.3",
  "org.scalatest" %% "scalatest" % "3.1.2" % "test"
)