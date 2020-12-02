package it.flatmap.fantaz

import java.io.IOException
import zio.{ExitCode, Has, IO, URIO, ZIO}
import zio.console._

object Fantaz extends zio.App {
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = myApplication.exitCode

  def output(name: String) = putStr(s"Hello ${name}")

  def getName = for {
    _ <-  putStrLn("Hello my friend, give me your name")
    name <- getStrLn
  } yield name

  def toUppercase(name: String) = IO.succeed(name.toUpperCase)

  private val value: ZIO[Console, IOException, Unit] = for {
    name <- getName
    uppercase <- toUppercase(name)
    - <- output(uppercase)
  } yield ()


  val myApplication = value
}
