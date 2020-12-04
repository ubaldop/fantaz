package it.flatmap.fantaz

import zio.{UIO, URIO, ZIO, ZLayer}
import zio.console.Console

object Logging {

  trait Service {
    def info(s: String): UIO[Unit]
  }

  val consoleLogger: ZLayer[Console, Nothing, Logging] = ZLayer.fromFunction(console =>
    new Service {
      def info(s: String): UIO[Unit] = console.get.putStrLn(s"info: $s")
    }
  )

  def info(s: String): URIO[Logging, Unit] = ZIO.accessM(_.get.info(s))
}
