package com.fortysevendeg.architecture.services.api

import commons.TaskService._

trait ApiService {

  def getAnimals(simulateFail: Boolean): TaskService[Seq[Animal]]

}
