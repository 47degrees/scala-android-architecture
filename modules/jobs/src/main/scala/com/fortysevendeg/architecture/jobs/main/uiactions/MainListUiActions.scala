package com.fortysevendeg.architecture.jobs.main.uiactions

import com.fortysevendeg.architecture.jobs.main.{AnimalJob, MainJobs}
import sarch.UiAction

trait MainListUiActions {

  def init(mainJobs: MainJobs): UiAction

  def loadAnimals(animals: Seq[AnimalJob]): UiAction

  def addItem(): UiAction

}
