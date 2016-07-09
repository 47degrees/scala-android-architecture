package com.fortysevendeg.architecture.jobs.main

import cats.data.Reader
import com.fortysevendeg.architecture.services.api.impl.ApiServiceImpl
import com.fortysevendeg.architecture.ui.commons.TasksOps._
import com.fortysevendeg.architecture.ui.main.transformations.MainBinding

class MainJobs {

  val apiService = new ApiServiceImpl

  def loadAnimals: Reader[MainBinding with MainListUiActions, Unit] = {
    Reader.apply((actions: MainBinding with MainListUiActions) => {
      apiService.getAnimals.resolveAsyncUi(
        onResult = actions.loadAnimals
      )
    })
  }
}
