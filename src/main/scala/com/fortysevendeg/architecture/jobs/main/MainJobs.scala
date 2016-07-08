package com.fortysevendeg.architecture.jobs.main

import cats.data.Xor
import com.fortysevendeg.architecture.services.api.impl.ApiServiceImpl
import com.fortysevendeg.scala.architecture.ui.main.ImageData

import scalaz.{-\/, \/-}
import scalaz.concurrent.Task

class MainJobs(actions: MainListUiActions) {

  val apiService = new ApiServiceImpl

  def loadAnimals(): Unit = {
    Task.fork(apiService.getAnimals).runAsync {
      case -\/(ex) =>
      case \/-(Xor.Left(ex)) =>
      case \/-(Xor.Right(data)) =>
        val imageData = data map (d => ImageData(d.name, d.url))
        actions.loadAnimals(imageData).run
    }
  }
}
