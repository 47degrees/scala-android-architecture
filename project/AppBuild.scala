/*
 *  Copyright (C) 2016 47 Degrees, LLC http://47deg.com hello@47deg.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may
 *  not use this file except in compliance with the License. You may obtain
 *  a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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