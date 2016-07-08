package com.fortysevendeg.architecture.jobs.main

import cats.data.{Reader, Xor}
import com.fortysevendeg.architecture.services.api.impl.ApiServiceImpl
import com.fortysevendeg.architecture.ui.main.adapters.ImageData

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
          val imageData = data map (d => ImageData(d.name, d.url))
          actions.loadAnimals(imageData).run
      }
    })
  }
}
