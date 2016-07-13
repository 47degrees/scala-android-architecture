package com.fortysevendeg.architecture.jobs.main

import com.fortysevendeg.architecture.services.api.Animal
import macroid.Ui
import sarch.UiAction

trait MainListUiActions {

  def init(): UiAction

  def loadAnimals(animals: Seq[Animal]): UiAction

  def addItem(): UiAction

}
