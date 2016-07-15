import sbt._
import Settings._

object AppBuild extends Build {

  lazy val androidScala = Project(
    id = "android",
    base = file("modules/android"),
    settings = android.Plugin.androidBuild ++ androidAppSettings
  ).dependsOn(services, commons)

  lazy val services = Project(id = "services", base = file("modules/services"))
    .settings(servicesSettings).dependsOn(commons)

  lazy val commons = Project(id = "commons", base = file("modules/commons"))
    .settings(sarchSettings)

}