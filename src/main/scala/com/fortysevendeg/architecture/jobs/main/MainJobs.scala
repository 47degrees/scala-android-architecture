package com.fortysevendeg.architecture.jobs.main

import cats.data.Reader
import com.fortysevendeg.architecture.jobs.Job
import com.fortysevendeg.architecture.services.api.Animal
import com.fortysevendeg.architecture.services.api.impl.ApiServiceImpl
import com.fortysevendeg.architecture.ui.commons.TasksOps._
import com.fortysevendeg.architecture.ui.main.transformations.{AnimalHolderBinding, MainBinding}

class MainJobs {

  val apiService = new ApiServiceImpl

  def initialize: Job[MainBinding with MainListUiActions] =
    Reader.apply((actions: MainBinding with MainListUiActions) => {
      actions.init().run
    }).flatMap(_ => loadAnimals)

  def loadAnimals: Job[MainBinding with MainListUiActions] =
    Reader.apply((actions: MainBinding with MainListUiActions) => {
      apiService.getAnimals.resolveAsyncUi(
        onResult = actions.loadAnimals
      )
    })

  def addItem: Job[MainBinding with MainListUiActions] =
    Reader.apply((actions: MainBinding with MainListUiActions) => {
      actions.addItem().run
    })

  def bindAnimal(animal: Animal): Job[AnimalHolderBinding with AnimalHolderUiActions] =
    Reader.apply((actions: AnimalHolderBinding with AnimalHolderUiActions) => {
      actions.bind(animal).run
    })

}
