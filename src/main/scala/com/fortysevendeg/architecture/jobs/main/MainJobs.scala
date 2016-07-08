package com.fortysevendeg.architecture.jobs.main

import cats.data.{Reader, Xor}
import com.fortysevendeg.architecture.services.api.impl.ApiServiceImpl

import scalaz.{-\/, \/-}
import scalaz.concurrent.Task

class MainJobs {

  val apiService = new ApiServiceImpl

  def loadAnimals: Reader[MainListUiActions, Unit] = {
    Reader.apply((actions: MainListUiActions) => {
      Task.fork(apiService.getAnimals).runAsync {
        case -\/(ex) =>
        case \/-(Xor.Left(ex)) =>
        case \/-(Xor.Right(data)) =>
          actions.loadAnimals(data).run
      }
    })
  }
}
