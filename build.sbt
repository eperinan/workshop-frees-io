pgpPassphrase := Some(getEnvVar("PGP_PASSPHRASE").getOrElse("").toCharArray)
pgpPublicRing := file(s"$gpgFolder/pubring.gpg")
pgpSecretRing := file(s"$gpgFolder/secring.gpg")

lazy val root = project
  .in(file("."))
  .settings(name := "workshop-frees-io")
  .settings(moduleName := "root")
  .settings(noPublishSettings: _*)
  .settings(scalaMetaSettings: _*)
  .settings(libraryDependencies ++= commonDeps ++ freestyleCoreDeps())
  .aggregate(`workshop-frees-ioJS`, `workshop-frees-ioJVM`)

lazy val `workshop-frees-io` = crossProject
  .in(file("workshop-frees-io"))
  .settings(moduleName := "workshop-frees-io")
  .settings(scalaMetaSettings: _*)
  .crossDepSettings(commonDeps ++ freestyleCoreDeps(): _*)
  .jsSettings(sharedJsSettings: _*)

lazy val `workshop-frees-ioJVM` = `workshop-frees-io`.jvm
lazy val `workshop-frees-ioJS` = `workshop-frees-io`.js
