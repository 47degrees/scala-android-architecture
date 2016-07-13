package com.fortysevendeg.architecture.jobs.main

import cats.data.Reader
import com.fortysevendeg.architecture.jobs.main.uiactions.{AnimalHolderUiActions, LoadingUiActions, MainListUiActions}
import sarch._
import com.fortysevendeg.architecture.services.api.impl.ApiServiceImpl
import sarch.TasksOps._
import Conversions._
import com.fortysevendeg.architecture.services.api.Animal

class MainJobs {

  val apiService = new ApiServiceImpl

  def initialize: Job[Binding with MainListUiActions with LoadingUiActions] =
    Reader((actions: Binding with MainListUiActions with LoadingUiActions) => {
      actions.init().run
    }).flatMap(_ => loadAnimals)

  def loadAnimals: Job[Binding with MainListUiActions with LoadingUiActions] =
    Reader((actions: Binding with MainListUiActions with LoadingUiActions) => {
      apiService.getAnimals.resolveAsyncUiAction(
        onPreTask = () => actions.showLoading(),
        onResult = (animals: Seq[Animal]) =>
          actions.showContent() + actions.loadAnimals(animals map toAnimalJob)
      )
    })

  def addItem: Job[Binding with MainListUiActions] =
    Reader((actions: Binding with MainListUiActions) => {
      actions.addItem().run
    })

  def bindAnimal(animal: AnimalJob): Job[Binding with AnimalHolderUiActions] =
    Reader((actions: Binding with AnimalHolderUiActions) => {
      actions.bind(animal).run
    })

}
