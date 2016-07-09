package com.fortysevendeg.architecture.jobs.main

import cats.data.{Reader, Xor}
import com.fortysevendeg.architecture.services.api.impl.ApiServiceImpl
import com.fortysevendeg.architecture.ui.main.transformations.MainBinding

import scalaz.concurrent.Task
import scalaz.{-\/, \/-}

class MainJobs {

  val apiService = new ApiServiceImpl

  def loadAnimals: Reader[MainBinding with MainListUiActions, Unit] = {
    Reader.apply((actions: MainBinding with MainListUiActions) => {
      Task.fork(apiService.getAnimals).runAsync {
        case -\/(ex) =>
        case \/-(Xor.Left(ex)) =>
        case \/-(Xor.Right(data)) =>
          actions.loadAnimals(data).run
      }
    })
  }
}
