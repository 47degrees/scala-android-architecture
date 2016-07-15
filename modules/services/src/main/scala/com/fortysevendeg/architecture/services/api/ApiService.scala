package com.fortysevendeg.architecture.services.api

import commons.Service._

trait ApiService {

  def getAnimals(simulateFail: Boolean): Service[Throwable, Seq[Animal]]

}
