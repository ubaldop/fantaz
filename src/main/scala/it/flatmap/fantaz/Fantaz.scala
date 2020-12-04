package it.flatmap.fantaz

import zio.config.getConfig
import zio.console._
import zio.{Has, ZIO}

object Fantaz extends zio.App {

  val config = Config.appConfig

  //defining a set of layers, since the required environment for my zio application is made of three modules
  val printPlayerData = Console.live ++ PlayerInfo.testPlayer ++ Logging.consoleLogger

  val configurationEnvironment: ZIO[Has[FantazConfig] with Console, Nothing, Unit] =
    for {
      _ <- putStrLn("Hello > ")
      appConfig <- getConfig[FantazConfig]
      _         <- putStrLn(appConfig.endpoint)
      _         <- putStrLn(appConfig.port.toString)
    } yield ()

  val logPlayer: ZIO[PlayerInfo with Logging with Console, Throwable, Unit] = for {
    _ <- putStrLn("give me the surname of your player...")
    surname <- getStrLn
    p <- PlayerInfo.getPlayer(surname)
    _ <- Logging.info(p.toString)
  } yield ()


  override def run(args: List[String]) =
    logPlayer.forever.provideLayer(printPlayerData).exitCode
    //configurationEnvironment.provideLayer(Console.live ++ config).exitCode
}
