package com.fortysevendeg.architecture.ui.main.jobs

import com.fortysevendeg.architecture.TypedFindView
import com.fortysevendeg.architecture.services.api.impl.ApiServiceImpl
import com.fortysevendeg.architecture.ui.main.transformations.{LoadingUiActionsImpl, MainBinding, MainListUiActionsImpl}
import commons.TaskService._
import macroid.ActivityContextWrapper
import cats.implicits._
import commons.TaskService

class MainJobs(tfv: TypedFindView)(implicit activityContextWrapper: ActivityContextWrapper) {

  val apiService = new ApiServiceImpl

  val uiActions = new MainBinding(this, tfv) with MainListUiActionsImpl with LoadingUiActionsImpl

  def initialize: TaskService[Unit] =
    uiActions.init()

  def loadAnimals: TaskService[Unit] = {
    for {
      _ <- uiActions.showLoading()
      animals <- apiService.getAnimals()
      _ <- uiActions.showContent()
      _ <- uiActions.loadAnimals(animals)
    } yield ()
  }

  def addItem(): TaskService[Unit] = uiActions.addItem()

  def showError: TaskService[(Unit, Unit)] =
    TaskService((uiActions.showError() |@| uiActions.displayError()).tupled.value)

}
