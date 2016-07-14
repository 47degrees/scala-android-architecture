import sbt._
import Settings._

object AppBuild extends Build {

  lazy val androidScala = Project(
    id = "android-main",
    base = file("modules/android"),
    settings = android.Plugin.androidBuild ++ androidAppSettings
  ).dependsOn(jobs, sarch)

  lazy val jobs = Project(id = "jobs", base = file("modules/jobs"))
    .settings(jobsSettings).dependsOn(services, sarch)

  lazy val services = Project(id = "services", base = file("modules/services"))
    .settings(servicesSettings).dependsOn(sarch)

  lazy val sarch = Project(id = "sarch", base = file("modules/sarch"))
    .settings(sarchSettings)

}