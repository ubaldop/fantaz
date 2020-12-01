name := "fantaz"

version := "0.1"

scalaVersion := "2.13.3"

val ZIOVersion = "1.0.3"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % ZIOVersion,
  "dev.zio" %% "zio-test" % ZIOVersion % "test",
  "dev.zio" %% "zio-test-sbt" % ZIOVersion % "test"
)
testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))