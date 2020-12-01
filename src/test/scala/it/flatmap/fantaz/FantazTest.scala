package it.flatmap.fantaz
import zio.test._
import zio.test.Assertion._

object FantazTest extends DefaultRunnableSpec {
  def spec = suite("Fantaz")(
    test("Test setup") {
      assert(true)(isTrue)
    }
  )
}
