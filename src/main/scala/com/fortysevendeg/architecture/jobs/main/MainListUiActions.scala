package com.fortysevendeg.architecture.jobs.main

import com.fortysevendeg.scala.architecture.ui.main.ImageData
import macroid.Ui

trait MainListUiActions {

  def loadAnimals(animals: Seq[ImageData]): Ui[Any]

}
