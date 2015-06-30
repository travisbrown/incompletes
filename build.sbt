lazy val sharedSettings = Seq(
  organization := "io.travisbrown",
  scalaVersion := "2.11.6",
  crossScalaVersions := Seq("2.10.5", "2.11.6"),
  resolvers ++= Seq(
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
  ),
  libraryDependencies ++= Seq(
    "com.chuusai" %% "shapeless" % "2.2.4-SNAPSHOT",
    "org.scala-lang" % "scala-compiler" % scalaVersion.value,
    "org.scalacheck" %% "scalacheck" % "1.12.4" % "test",
    "org.scalatest" %% "scalatest" % "2.2.5" % "test",
    "org.scalaz" %% "scalaz-core" % "7.1.1"
  )
)

lazy val root = project.in(file("."))
  .settings(sharedSettings)
  .aggregate(core, demo)
  .dependsOn(core)

lazy val core = project
  .settings(moduleName := "incompletes")
  .settings(sharedSettings)

lazy val demo = project
  .settings(moduleName := "incompletes-demo")
  .settings(sharedSettings)
  .settings(
    libraryDependencies +=
      "com.github.alexarchambault" %% "argonaut-shapeless_6.1" % "0.2.0"
  ).dependsOn(core)
