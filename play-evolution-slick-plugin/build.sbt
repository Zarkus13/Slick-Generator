name := "play-evolution-slick-plugin"

version := "0.1.0"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.2.1",
  "com.typesafe.play" %% "play-jdbc" % "2.2.1"
)

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"