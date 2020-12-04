package it.flatmap.fantaz
import zio.{Has, IO, Layer, UIO, URIO, ZIO, ZLayer}


sealed trait Role
case object Goalkeeper extends Role
case object Defender extends Role
case object Midfielder extends Role
case object Striker extends Role

case class PlayerFantazData(fantaMean: Double, gamesPlayed: Integer,
                            goals: Integer, assists: Integer,
                            yellowCards: Integer, redCards: Integer)

case class Player(name: String, surname: String, team: String, role: Role, data: PlayerFantazData)


/**
 * ZIO module
 */
object PlayerInfo {

  //This interface represents the interface our module is exposing
  trait Service {
      def getPlayer(surname: String): UIO[Option[Player]]
  }

  //implementation of the exposed module through a ZLayer
  val testPlayer: Layer[Nothing, PlayerInfo] = ZLayer.succeed(
    new Service {
      override def getPlayer(surname: String): UIO[Option[Player]] = {
        if(surname.startsWith("a")) {
          val fantazdata = PlayerFantazData(10.2, 10, 2, 0, 5, 0);
          UIO(Some(Player("Luca", "Caldirola", "Benevento", Defender, fantazdata)))
        } else {
          val fantazdata = PlayerFantazData(10.2, 10, 0, 0, 5, 0);
          UIO(Some(Player("Kamil", "Glik", "Benevento", Defender, fantazdata)))
        }
      }
    }
  )

  def getPlayer(surname: String): URIO[PlayerInfo, Option[Player]] = ZIO.accessM(_.get.getPlayer(surname))
}
