package it.flatmap

import zio.{Task, ZIO}

package object fantaz {

  case class Config(api: ApiConfig)
  case class ApiConfig(endpoint: String, port: Int)

  object Configuration {
   trait Service {
     val load = Task[Config]
   }
  }

  type Configuration = zio.Has[Configuration.Service]

  val load: ZIO[Configuration, Throwable, Config] = ZIO.accessM(_.get.load)
}
