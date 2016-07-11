import Versions._
import android.Keys._
import sbt.Keys._
import sbt._
import Settings._

object AppBuild extends Build {

  lazy val androidScala = Project(
    id = "android-main",
    base = file("modules/android"),
    settings = android.Plugin.androidBuild ++ androidAppSettings
  ).dependsOn(jobs)

  //  def excludeArtifact(module: ModuleID, artifactOrganizations: String*): ModuleID =
  //    module.excludeAll(artifactOrganizations map (org => ExclusionRule(organization = org)): _*)
  //
  //  lazy val root = Project(id = "root", base = file("."))
  //    .settings(
  //      scalaVersion := scalaV,
  //      version := appV,
  //      name := "scala-android-architecture",
  //      scalacOptions ++= Seq("-feature", "-deprecation"),
  //      platformTarget in Android := "android-23",
  //      packageRelease <<= packageRelease in Android in androidApp,
  //      packageDebug <<= packageDebug in Android in androidApp,
  //      install <<= install in Android in androidApp,
  //      run <<= run in Android in androidApp,
  //      applicationId in Android := "com.fortysevendeg.architecture"
  //    )
  //    .aggregate(androidApp)
  //
  //  lazy val androidApp = Project(id = "android", base = file("modules/android"))
  //    .settings(projectDependencies ~= (_.map(excludeArtifact(_, "com.android"))))
  //    .settings(
  //      outputLayout in Android <<= (outputLayout in Android),
  //      packageResources in Android <<= (packageResources in Android)
  //    )
  //    .settings(androidAppSettings: _*)
  //    .androidBuildWith(jobs)
  //
  lazy val jobs = Project(id = "jobs", base = file("modules/jobs"))
    .settings(jobsSettings).dependsOn(services)
  //
  lazy val services = Project(id = "services", base = file("modules/services"))
    .settings(servicesSettings: _*)

}