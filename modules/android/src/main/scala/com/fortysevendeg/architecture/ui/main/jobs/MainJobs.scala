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

package com.fortysevendeg.architecture.ui.main.jobs

import cats.implicits._
import com.fortysevendeg.architecture.services.api.impl.ApiServiceImpl
import commons.TaskService
import commons.TaskService._
import macroid.ActivityContextWrapper

class MainJobs(ui: MainListUiActions)(implicit activityContextWrapper: ActivityContextWrapper) {

  val apiService = new ApiServiceImpl

  def initialize: TaskService[Unit] = ui.init(this)

  def loadAnimals: TaskService[Unit] = {
    for {
      _ <- ui.showLoading()
      animals <- apiService.getAnimals()
      _ <- ui.showContent()
      _ <- ui.loadAnimals(animals)
    } yield ()
  }

  def addItem(): TaskService[Unit] = ui.addItem()

  def showError: TaskService[(Unit, Unit)] =
    TaskService((ui.showError() |@| ui.displayError()).tupled.value)

}
