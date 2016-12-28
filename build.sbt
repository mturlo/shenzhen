name := "shenzhen"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.8"

libraryDependencies ++= {

  object Versions {
    val scalatest = "3.0.0"
  }

  Seq(
    "org.scalatest" %% "scalatest" % Versions.scalatest % "test"
  )

}

scalacOptions ++= Seq("-Xfatal-warnings", "-feature", "-language:postfixOps")

lazy val root = project in file(".")

// scalastyle check on compile

lazy val compileScalastyle = taskKey[Unit]("compileScalastyle")

compileScalastyle := org.scalastyle.sbt.ScalastylePlugin.scalastyle.in(Compile).toTask("").value

(compile in Compile) := ((compile in Compile) dependsOn compileScalastyle).value
