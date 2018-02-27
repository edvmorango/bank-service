name := "bank-service"

version := "0.1"

scalaVersion := "2.12.4"
javacOptions in (Compile, compile) ++= Seq("--release", "9") ++ Seq("--add-modules=java.activation")
crossScalaVersions := Seq("2.11.11", "2.12.4")
testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v", "-s", "-a")


resolvers ++= Seq("twttr" at "https://maven.twttr.com/",
              "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/releases/")



libraryDependencies ++= Seq(
  "com.twitter" %% "finatra-http" % "18.2.0",

  "com.twitter" %% "inject-app" % "18.2.0" % "test",
  "com.twitter" %% "inject-core" % "18.2.0" % "test",
  "com.twitter" %% "inject-modules" % "18.2.0" % "test",
  "com.twitter" %% "inject-server" % "18.2.0" % "test",
  "com.twitter" %% "inject-utils" % "18.2.0" % "test",

  "org.typelevel" %% "cats-core" % "1.0.1",
  "com.jakehschwartz" % "finatra-swagger_2.12" % "17.12.0",
  "org.postgresql" % "postgresql" % "9.4.1208",
  "com.typesafe.slick" %% "slick" % "3.2.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.1",

  "com.twitter" %% "finatra-http" % "18.2.0" % "test" classifier "tests",
  "com.twitter" %% "inject-app" % "18.2.0" % "test" classifier "tests",
  "com.twitter" %% "inject-core" % "18.2.0" % "test"  classifier "tests",
  "com.twitter" %% "inject-modules" % "18.2.0" % "test"  classifier "tests",
  "com.twitter" %% "inject-server" % "18.2.0" % "test"  classifier "tests",

  "com.google.inject.extensions" % "guice-testlib" % "4.0" % "test",

  "com.typesafe.slick" %% "slick-testkit" % "3.2.1" % "test",

  "org.mockito" % "mockito-core" % "1.9.5" % "test", // Feature tests don't work with mockito 2
  "org.scalatest" %% "scalatest" % "3.0.4" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
)
