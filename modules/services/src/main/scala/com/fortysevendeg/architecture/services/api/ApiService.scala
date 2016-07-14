package com.fortysevendeg.architecture.services.api

import sarch.Service._

trait ApiService {

  def getAnimals: Service[Exception, Seq[Animal]]

}
