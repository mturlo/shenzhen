name := "shenzhen"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.8"

libraryDependencies ++= {

  object Versions {
    val parserCombinators = "1.0.5"
    val scalatest = "3.0.0"
  }

  Seq(

    // prod
    "org.scala-lang.modules" %% "scala-parser-combinators" % Versions.parserCombinators,

    // test
    "org.scalatest" %% "scalatest" % Versions.scalatest % "test"

  ).map(_ withSources() withJavadoc())

}

scalacOptions ++= Seq(
  "-Xfatal-warnings",
  "-feature",
  "-deprecation",
  "-language:postfixOps"
)

lazy val root = project in file(".")

// scalastyle check on compile

lazy val compileScalastyle = taskKey[Unit]("compileScalastyle")

compileScalastyle := org.scalastyle.sbt.ScalastylePlugin.scalastyle.in(Compile).toTask("").value

(compile in Compile) := ((compile in Compile) dependsOn compileScalastyle).value
