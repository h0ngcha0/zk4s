package it.softfork.zk4s

import tech.pegasys.teku.bls.impl.mikuli.MikuliBLS12381
import tech.pegasys.teku.bls.impl.mikuli.G1Point
import tech.pegasys.teku.bls.impl.mikuli.G2Point
import tech.pegasys.teku.bls.impl.mikuli.AtePairing._
import tech.pegasys.teku.bls.impl.mikuli.Scalar
import org.apache.milagro.amcl.BLS381._
import org.apache.milagro.amcl.BLS381.ROM
import Implicits._

class BLSSpec extends Spec {

  "BLS381 points multiply order" should "be infinity" in {
    val p1 = G1Point.random(1L)
    val q2 = G2Point.random(2L)

    val order = new Scalar(new BIG(ROM.CURVE_Order))

    p1.mul(order).getPoint().is_infinity() shouldEqual true
    q2.mul(order).getPoint().is_infinity() shouldEqual true
  }

  "BLS paring" should "work" in {

    val p1 = G1Point.random(1L)
    val q2 = G2Point.random(2L)

    val three = 3.toScalar
    val seven = 7.toScalar

    pair(p1.mul(three), q2.mul(seven)) shouldEqual pair(p1.mul(seven), q2.mul(three))
  }
}
