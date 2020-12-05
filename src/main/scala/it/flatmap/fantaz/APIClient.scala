package it.flatmap.fantaz

import org.http4s.FormDataDecoder.formEntityDecoder
import org.http4s.client.Client
import org.http4s.client.dsl.Http4sClientDsl
import zio.{Has, RIO, Task, URIO, URLayer, ZIO, ZLayer}
import zio.interop.catz._

//API Client module
object APIClient {

  type APIClient = Has[Service]

  trait Service {
    def invoke(uri: String, parameters: Map[String, String]): Task[String]
  }

  def invoke(s: String, parameters: Map[String, String]): ZIO[APIClient, Throwable, Task[String]] = ZIO.access(_.get.invoke(s, parameters))

  def http4s: URLayer[Has[Client[Task]], APIClient] = ZLayer.fromService[Client[Task], Service] { http4sClient =>
    Http4sClient(http4sClient)
  }
}


private final case class Http4sClient(client: Client[Task]) extends APIClient.Service with Http4sClientDsl[Task] {
  override def invoke(uri: String, parameters: Map[String, String]): Task[String] = client.expect[String]("TODO-URL")
}