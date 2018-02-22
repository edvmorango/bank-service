name := "bank-service"

version := "0.1"

scalaVersion := "2.12.4"
scalacOptions += "-target:jvm-1.8"


resolvers ++= Seq("twttr" at "https://maven.twttr.com/",
              "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/releases/")


libraryDependencies ++= Seq(
  "com.twitter" %% "finatra-http" % "18.2.0" ,
  "com.jakehschwartz" % "finatra-swagger_2.12" % "17.12.0",
  "org.postgresql" % "postgresql" % "9.4.1208",
  "com.typesafe.slick" %% "slick" % "3.2.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.1",
  "com.twitter" %% "finatra-http" % "18.2.0" % "test" classifier "tests",
  "org.scalatest" %% "scalatest" % "3.0.4" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
)
