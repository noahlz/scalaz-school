package com.novus.codeschool.scalaz

case class NotNumericException(msg: String) extends IllegalArgumentException(msg)
case class NumericResultHolder(result: String)

object NumericResultHolder {
  def apply(ex: NotNumericException): NumericResultHolder =
    NumericResultHolder(s"Exception happened: $ex")
}

object NaiveParser {

  def isNumber(input: String) = input.forall(c => c.isDigit)

  def apply(input: String) = if(isNumber(input)) {
    input.toLong
  } else {
    new NotNumericException(input)
  }
}

