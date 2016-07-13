package com.fortysevendeg.architecture.jobs.main.uiactions

import com.fortysevendeg.architecture.jobs.main.AnimalJob
import sarch.UiAction

trait MainListUiActions {

  def init(): UiAction

  def loadAnimals(animals: Seq[AnimalJob]): UiAction

  def addItem(): UiAction

}
