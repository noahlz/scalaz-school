package com.novus.codeschool.scalaz

import scalaz._

object Disjunctions {

  def matching() {

    val foo: \/[String, Int] = \/-(100)

    foo match {
      case \/-(value) => println(s"Right value: $value")
      case -\/(value) => println(s"Left value: $value")
    }
  }

  def folding() {

    def resolve(input: String \/ Int): Unit = {
      val result: Any = input.fold({ left =>
        left + 200
      },{ right =>
        right + 200
      })
      println(s"Result: $result")
    }

    val bazz = -\/("200")
    val bar = \/-(200)

    resolve(bazz) // 200200
    resolve(bar) // 400
  }

  def mapping() = {

    val fn = (i: Int) => i + 100

    val number: String \/ Int = \/-(100)
    val mappedNumber = number.map(fn)
    println(mappedNumber) // \/-(200)

    val string: String \/ Int = -\/("-100")
    val mappedString = string.map(fn)
    println(mappedString) // -\/("-100")
  }

  ////////////////////////////////////////////////////////////
  // valueOr

  def simpleParseNumeric(input: String): NotNumericException \/ NumericResultHolder = {
    if(input.forall(c => c.isDigit)) {
      val result = input.toLong
      \/-(NumericResultHolder(s"$result"))

    } else {
      -\/(new NotNumericException(s"Not a simple number: $input"))
    }
  }

  def valueOr() {
    val result = simpleParseNumeric("100") valueOr NumericResultHolder.apply
    println(result)

    val bad = simpleParseNumeric("100o") valueOr NumericResultHolder.apply
    print(bad)
  }

}
