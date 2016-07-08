import Libraries.android._
import Libraries.macroid._
import Libraries.scalaz._
import Libraries.cats._
import Libraries.graphics._
import Libraries.json._
import Libraries.test._

android.Plugin.androidBuild

platformTarget in Android := Versions.androidPlatformV

name := "scala-android-architecture"

organization := "com.fortysevendeg"

organizationName := "47 Degrees"

organizationHomepage := Some(new URL("http://47deg.com"))

version := Versions.appV

scalaVersion := Versions.scalaV

scalacOptions ++= Seq("-feature", "-deprecation")

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

scalacOptions ++= Seq("-feature", "-deprecation", "-target:jvm-1.7")

resolvers ++= Settings.resolvers

libraryDependencies ++= Seq(
  aar(macroidRoot),
  aar(androidDesign),
  aar(androidCardView),
  aar(androidRecyclerview),
  aar(macroidExtras),
  scalaz,
  cats,
  playJson,
  picasso,
  specs2,
  mockito,
  androidTest)

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

dexMainClassesConfig := baseDirectory.value / "copy-of-maindexlist.txt"

transitiveAndroidLibs in Android := true

run <<= run in Android

proguardScala in Android := true

useProguard in Android := true

useProguardInDebug in Android := true

proguardCache in Android := Seq.empty

proguardOptions in Android ++= Settings.proguardCommons ++ Settings.proguardAkka

packagingOptions in Android := PackagingOptions(
  Seq("META-INF/LICENSE",
    "META-INF/LICENSE.txt",
    "META-INF/NOTICE",
    "META-INF/NOTICE.txt"))

dexMaxHeap in Android := "2048m"

dexMulti in Android := true

