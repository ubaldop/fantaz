name := "fantaz"

version := "0.1"

scalaVersion := "2.13.3"

val ZIOVersion = "1.0.3"

val httpsVersion = "0.21.1"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % ZIOVersion,
  "org.http4s" %% "http4s-blaze-server" % httpsVersion,
  "org.http4s" %% "http4s-circe" % httpsVersion,
  "org.http4s" %% "http4s-dsl" % httpsVersion,
  "dev.zio" %% "zio-interop-cats" % "2.0.0.0-RC12",   // TODO changeme
  "dev.zio" %% "zio-test" % ZIOVersion % "test",
  "dev.zio" %% "zio-test-sbt" % ZIOVersion % "test"
)
testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))