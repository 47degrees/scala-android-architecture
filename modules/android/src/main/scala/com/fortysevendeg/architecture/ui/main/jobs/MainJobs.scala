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
