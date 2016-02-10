name          := "codeschool-scalaz"

organization  := "com.novus"

version       := "0.1.0"

scalaVersion  := "2.11.7"

scalacOptions ++= Seq(
  "-feature",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint",
  "-encoding",
  "utf8"
)

libraryDependencies ++= {
  val scalazV = "7.1.0"
  Seq(
    "org.scalaz"  %%  "scalaz-core" % scalazV
  )
}

initialCommands in console := """
    |import scalaz._
    |import com.novus.codeschool.scalaz._
  """.stripMargin
