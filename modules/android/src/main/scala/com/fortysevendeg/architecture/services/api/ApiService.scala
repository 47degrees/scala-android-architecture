package com.fortysevendeg.architecture.services.api

import cats.data.Xor
import scalaz.concurrent.Task

trait ApiService {

  def getAnimals: Task[Exception Xor Seq[Animal]]

}
