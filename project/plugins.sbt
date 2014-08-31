logLevel := Level.Warn

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.2")

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "DiversIT repo" at "http://repository-diversit.forge.cloudbees.com/release"

addSbtPlugin("eu.diversit.sbt.plugin" % "webdav4sbt" % "1.3")