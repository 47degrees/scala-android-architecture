package com.fortysevendeg.architecture.jobs.main

import cats.data.Xor
import com.fortysevendeg.architecture.jobs.main.Conversions._
import com.fortysevendeg.architecture.jobs.main.uiactions.{AnimalHolderUiActions, LoadingUiActions, MainListUiActions}
import com.fortysevendeg.architecture.services.api.impl.ApiServiceImpl
import sarch.Service._
import sarch._

import scalaz.concurrent.Task

class MainJobs(
  listActions: Binding with MainListUiActions with LoadingUiActions,
  rowActions: Binding with AnimalHolderUiActions) {

  val apiService = new ApiServiceImpl

  def initialize: Service[Exception, Unit] =
    Service(Task {
      Xor.Right(listActions.init(this).run)
    })

  def loadAnimals: Service[Exception, Unit] = {
    for {
      _ <- Service(Task {
        Xor.Right {
          listActions.showLoading().run
        }
      })
      animals <- apiService.getAnimals
      _ <- Service(Task {
        Xor.Right {
          (listActions.showContent() + listActions.loadAnimals(animals map toAnimalJob)).run
        }
      })
    } yield (())
  }

  def addItem: Service[Exception, Unit] =
    Service (Task {
        Xor.Right(listActions.addItem().run)
      })

  def bindAnimal(animal: AnimalJob): Service[Exception, Unit] =
    Service(Task {
      Xor.Right(rowActions.bind(animal).run)
    })

}
