package it.flatmap

import zio.Has

package object fantaz {
    // Has[A] represents a dependency on a service of type A
    type PlayerInfo = Has[PlayerInfo.Service]
    type Logging = Has[Logging.Service]
}
