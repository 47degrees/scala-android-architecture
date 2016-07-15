package com.fortysevendeg.architecture.ui.main.jobs

import com.fortysevendeg.architecture.TypedFindView
import com.fortysevendeg.architecture.services.api.impl.ApiServiceImpl
import com.fortysevendeg.architecture.ui.main.transformations.{LoadingUiActionsImpl, MainBinding, MainListUiActionsImpl}
import commons.Service._
import macroid.ActivityContextWrapper
import cats.implicits._
import commons.Service

class MainJobs(tfv: TypedFindView)(implicit activityContextWrapper: ActivityContextWrapper) {

  val apiService = new ApiServiceImpl

  val uiActions = new MainBinding(this, tfv) with MainListUiActionsImpl with LoadingUiActionsImpl

  def initialize: Service[Throwable, Unit] =
    uiActions.init()

  def loadAnimals: Service[Throwable, Unit] = {
    for {
      _ <- uiActions.showLoading()
      animals <- apiService.getAnimals()
      _ <- uiActions.showContent()
      _ <- uiActions.loadAnimals(animals)
    } yield ()
  }

  def addItem(): Service[Throwable, Unit] = uiActions.addItem()

  def showError: Service[Throwable, (Unit, Unit)] =
    Service((uiActions.showError() |@| uiActions.displayError()).tupled.value)

}
