package com.novus.codeschool.scalaz

import scalaz._
import scalaz.Scalaz._


object Categories {
  trait ExpCatCode

  //  https://www.seancassidy.me/strings-are-untyped.html
  def ExpCatCode(s: String): String @@ ExpCatCode = Tag[String, ExpCatCode](s)
}

/**
 * Shamelessly cribbed from http://bleibinha.us/blog/2014/12/scalaz-for-dummies
 */
object Tagging {
  type ExpCatCode = String @@ Categories.ExpCatCode

  val Sector = Categories.ExpCatCode("I")
  val Geography = Categories.ExpCatCode("G")
  val NamesLookup = Map(Sector -> "Sector")

  def example() {
    def foo(expCatCode: ExpCatCode) = println(expCatCode)
    // foo("I") // does not compile
    foo(Sector)
    foo(Geography)
  }
}

object TypeSafeEquals {
  def basics() {
    // println("""Does 1 === "1" ?""")
    // println(s"${1 === "1"}") // does not compile

    println("""Does 1 === 1 ?""")
    println(s"${1 === 1}") // does not compile

  }

  def lists() = {
    val stuff: List[Any] = List("1", 2, "3", '4', 5)
    println(s"""Does $stuff contain Int 2?""")
    println(stuff.contains(2 === _))

    println(s"""Does it contain Int 3?""")
    println(stuff.contains(3 === _))
  }

}

object Options {

  def example() {
    val one = 1.some
    val noInt = none[Int]

    def something_?(i: Option[Int]) = println(i)

    something_?(one)
    something_?(noInt)

  }

  def folding() {
    def someString(i: Option[Int]) = i.fold(none[String])(_.toString.some)

    // does not compile
    // def someStringBroken(i: Option[Int]) = i.fold(None)(s => Some(s.toString))

    println(someString(10.some))
    println(someString(none[Int]))
  }

}

