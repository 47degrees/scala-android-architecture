package com.fortysevendeg.architecture.ui.main.jobs

import com.fortysevendeg.architecture.services.api.impl.ApiServiceImpl
import com.fortysevendeg.architecture.ui.main.transformations.{LoadingUiActionsImpl, MainBinding, MainListUiActionsImpl}
import commons.Service._

class MainJobs(listActions: MainBinding with MainListUiActionsImpl with LoadingUiActionsImpl) {

  val apiService = new ApiServiceImpl

  def initialize: Service[Throwable, Unit] =
    listActions.init(this)

  def loadAnimals: Service[Throwable, Unit] = {
    for {
      _ <- listActions.showLoading()
      animals <- apiService.getAnimals
      _ <- listActions.showContent()
      _ <- listActions.loadAnimals(animals)
    } yield (())
  }

  def addItem: Service[Throwable, Unit] = listActions.addItem()

}
