package it.softfork.zk4s

import org.apache.milagro.amcl.BLS381.BIG
import tech.pegasys.teku.bls.impl.mikuli.Scalar

object Implicits {

  implicit class RichBigInt(value: BigInt) {

    def toBIG: BIG = {
      val byteArray = value.toByteArray

      val paddedByteArray = if (byteArray.length < BIG.MODBYTES) {
        val paddingLength = BIG.MODBYTES - byteArray.length
        new Array[Byte](paddingLength) ++ byteArray
      } else {
        byteArray
      }

      BIG.frombytearray(paddedByteArray, 0)
    }

    def toScalar: Scalar = {
      new Scalar(toBIG)
    }
  }

  implicit class RichBIG(value: BIG) {

    def toBigInt: BigInt = {
      val byteArray = new Array[Byte](BIG.MODBYTES)

      value.tobytearray(byteArray, 0)

      BigInt(byteArray)
    }
  }

  implicit class RichInt(value: Int) {
    def toScalar: Scalar = {
      new Scalar(new BIG(value))
    }
  }
}
