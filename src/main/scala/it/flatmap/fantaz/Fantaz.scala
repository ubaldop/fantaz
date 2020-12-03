package it.flatmap.fantaz

import zio.config.getConfig
import zio.console._
import zio.{Has, ZIO}

object Fantaz extends zio.App {
  
  val config = Config.appConfig

  val configurationEnvironment: ZIO[Has[FantazConfig] with Console, Nothing, Unit] =
    for {
      _ <- putStrLn("Hello > ")
      appConfig <- getConfig[FantazConfig]
      _         <- putStrLn(appConfig.endpoint)
      _         <- putStrLn(appConfig.port.toString)
    } yield ()


  override def run(args: List[String]) =
    configurationEnvironment.provideLayer(Console.live ++ config).exitCode
}
