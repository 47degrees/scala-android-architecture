package com.fortysevendeg.architecture.jobs.main

import com.fortysevendeg.architecture.services.api.Animal
import macroid.Ui

trait MainListUiActions {

  def init(): Ui[Any]

  def loadAnimals(animals: Seq[Animal]): Ui[Any]

}
