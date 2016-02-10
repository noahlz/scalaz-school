package com.novus.codeschool.scalaz

import scalaz._
import scalaz.syntax.validation._
import scalaz.syntax.applicative._

object Validations {

  def validate(input: String): Validation[NotNumericException, Long] =
    if(NaiveParser.isNumber(input))
      Success(input.toLong)
    else
      Failure(NotNumericException(input))

  val DefaultResult = -1

  def failureHandler(ex: NotNumericException) = {
    println(s"error happened: $ex")
    DefaultResult
  }

  def simple() {
    val validation = validate("1000")

    val value: Long = validation.valueOr(failureHandler)
    println(s"Result: $value")

    val badValue: Long = validate("100o").valueOr(failureHandler)
    println(s"Result: $badValue")
  }

  def justRethrow() {
    val validation = validate("100o")
    val value = validation.valueOr(ex => throw ex)
  }

  def fromTryCatchNonFatal() {
    val validation = Validation.fromTryCatchNonFatal("100o".toLong)
    println(validation)
  }

  // Links I looked that researching this
  // http://johnkurkowski.com/posts/accumulating-multiple-failures-in-a-ValidationNEL/
  // https://gist.github.com/manjuraj/2e27a1b66e7e0be697ca
  // http://bleibinha.us/blog/2014/12/scalaz-for-dummies
  // http://www.47deg.com/blog/fp-for-the-average-joe-part-1-scalaz-validation

  val Inputs = "1" :: "2" :: "NOT A NUMBER?!?" :: "4" :: "wat" :: Nil

  def validation(input: String): Validation[NotNumericException, Long] =
    if(NaiveParser.isNumber(input)) input.toLong.success else NotNumericException(input).failure

  def validationNel(input: String): ValidationNel[NotNumericException, List[Long]] =
    if(NaiveParser.isNumber(input)) {
      // Ugh. Ensures that we have a return type of List[Long]
      // There is probably a better way using type inference / sequenceU / implicitly
      (input.toLong :: Nil).successNel
    } else {
      new NotNumericException(input).failureNel
    }

  def fold() {

    import scalaz.std.list._

    val validations = Inputs.map(validationNel).reduce(_ +++ _)

    validations.fold({ failures =>
      failures.foreach(println)
    },{ successes =>
      successes.foreach(println)
    })
  }

  // http://stackoverflow.com/q/11903968/7507
  // https://github.com/mpilquist/scalaz-talk/blob/master/examples.scala

  def partitionUsualWay() {
    val (successes, failures) = Inputs.map(validation).partition(_.isSuccess)

    successes.foreach(println)
    failures.foreach(println)
  }

  // http://stackoverflow.com/questions/19485683/partition-a-sequence-of-disjunctions-in-scalaz
  // http://stackoverflow.com/a/32824369/7507

  def separate(): (List[NotNumericException], List[Long]) = {

    // TODO: import just what we need...
    import scalaz.Scalaz._

    val results = Inputs.map(validation).separate
    val (successes, failures) = results

    println(successes)
    println(failures)

    results
  }
}
