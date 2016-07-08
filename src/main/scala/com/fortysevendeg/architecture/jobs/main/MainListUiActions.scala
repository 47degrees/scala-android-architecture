package com.fortysevendeg.architecture.jobs.main

import com.fortysevendeg.architecture.ui.main.adapters.ImageData
import macroid.Ui

trait MainListUiActions {

  def loadAnimals(animals: Seq[ImageData]): Ui[Any]

}
