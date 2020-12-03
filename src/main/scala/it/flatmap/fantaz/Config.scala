package it.flatmap.fantaz

import zio.{ZIO, config}
import zio.config.magnolia.DeriveConfigDescriptor.descriptor
import zio.config.typesafe.TypesafeConfigSource

case class FantazConfig(endpoint: String, port: Int)
object Config {
    val appConfig = loadConfig.orDie.toLayer

    private def loadConfig =
      for {
        source <- ZIO.fromEither(TypesafeConfigSource.fromDefaultLoader)
        config <- ZIO.fromEither(config.read(descriptor[FantazConfig] from source))
      } yield config
}
