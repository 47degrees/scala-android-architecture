package com.fortysevendeg.architecture.services.api

import commons.Service._

trait ApiService {

  def getAnimals: Service[Throwable, Seq[Animal]]

}
