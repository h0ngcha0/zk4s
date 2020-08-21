package it.softfork.zk4s

import tech.pegasys.teku.bls.impl.mikuli.G1Point
import tech.pegasys.teku.bls.impl.mikuli.Scalar
import org.apache.milagro.amcl.BLS381._
import Implicits._

class EllipticCurveSpec extends Spec {
  "Diffie-Hellman algorithm" should "work with elliptic curve" in {
    val g = G1Point.random(1L)
    val kₐ = {
      val bigInt = BigInt("31415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679")

      bigInt.toScalar
    }
    val kₑ = {
      val bigInt = BigInt("27182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274")

      bigInt.toScalar
    }

    val Pₐ = g.mul(kₐ)
    val Pₑ = g.mul(kₑ)

    Pₑ.mul(kₐ) shouldEqual Pₐ.mul(kₑ)
  }

  "ROM values in BIG type" should "match with the values in BigInt type (documentation purpose)" in {
    val curveOrder = new BIG(ROM.CURVE_Order)
    curveOrder.toBigInt shouldEqual {
      BigInt("52435875175126190479447740508185965837690552500527637822603658699938581184513")
    }

    val curveModulus = new BIG(ROM.Modulus)
    curveModulus.toBigInt shouldEqual {
      BigInt("4002409555221667393417789825735904156556882819939007885332058136124031650490837864442687629129015664037894272559787")
    }

    val g = {
      val gx = new BIG(ROM.CURVE_Gx)
      val gy = new BIG(ROM.CURVE_Gy)

      (gx.toBigInt, gy.toBigInt)
    }

    g shouldEqual (
      BigInt("3685416753713387016781088315183077757961620795782546409894578378688607592378376318836054947676345821548104185464507"),
      BigInt("1339506544944476473020471379941921221584933875938349620426543736416511423956333506472724655353366534992391756441569")
    )

    val g2x = {
      val xa = new BIG(ROM.CURVE_Pxa)
      val xb = new BIG(ROM.CURVE_Pxb)

      (xa.toBigInt, xb.toBigInt)
    }

    g2x shouldEqual (
      BigInt("352701069587466618187139116011060144890029952792775240219908644239793785735715026873347600343865175952761926303160"),
      BigInt("3059144344244213709971259814753781636986470325476647558659373206291635324768958432433509563104347017837885763365758")
    )

    val g2y = {
      val ya = new BIG(ROM.CURVE_Pya)
      val yb = new BIG(ROM.CURVE_Pyb)

      (ya.toBigInt, yb.toBigInt)
    }

    g2y shouldEqual (
      BigInt("1985150602287291935568054521177171638300868978215655730859378665066344726373823718423869104263333984641494340347905"),
      BigInt("927553665492332455747201965776037880757740193453592970025027978793976877002675564980949289727957565575433344219582")
    )
  }
}
