name := "fantaz"

version := "0.1"

scalaVersion := "2.13.3"

val ZIOVersion = "1.0.3"

val httpsVersion = "0.21.1"
val zioConfigVersion = "1.0.0-RC30-1"

val http4sVersion = "0.21.12"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % ZIOVersion,
  "dev.zio"              %% "zio-config"           % zioConfigVersion,
  "dev.zio"              %% "zio-config-magnolia"  % zioConfigVersion,
  "dev.zio"              %% "zio-config-typesafe"  % zioConfigVersion,

  //HTTP4S
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,

  "dev.zio" %% "zio-interop-cats" % "2.0.0.0-RC12",   // TODO changeme
  "dev.zio" %% "zio-test" % ZIOVersion % "test",
  "dev.zio" %% "zio-test-sbt" % ZIOVersion % "test"
)
testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))