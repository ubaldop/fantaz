package it.flatmap.fantaz
import org.http4s.client.Client
import org.http4s.client.blaze.BlazeClientBuilder
import zio.interop.catz._
import zio.{ExitCode, RIO, Task, TaskManaged, UIO, URIO, ZEnv, ZIO}
import zio.console._

import scala.concurrent.ExecutionContext.Implicits

object DummyAPICall extends zio.App {

  private def makeHttpClient: UIO[TaskManaged[Client[Task]]] =
    ZIO.runtime[Any].map { implicit rts =>
      BlazeClientBuilder
        .apply[Task](Implicits.global)    //this implicit has been resolved through catz
        .resource
        .toManaged
    }

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {
    val program = for {
      http4sClient <- makeHttpClient
      code <- makeProgram(http4sClient)
    } yield ()

    program.exitCode
  }

  private def makeProgram(http4sClient: TaskManaged[Client[Task]]): RIO[ZEnv, Unit] = {

    val httpClientLayer = http4sClient.toLayer.orDie
    val http4sClientLayer = (Console.live ++ httpClientLayer) >>> (APIClient.http4s ++ Logging.consoleLogger)

    val httpCall = for {
      result <- APIClient.invoke("", Map("" -> ""))
      _ <- Logging.info(result.toString)
    } yield ()

    httpCall.provideSomeLayer[ZEnv](http4sClientLayer)
  }
}
