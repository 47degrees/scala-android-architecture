package com.fortysevendeg.architecture.jobs.main.uiactions

import com.fortysevendeg.architecture.jobs.main.AnimalJob
import sarch.UiAction

trait AnimalHolderUiActions {

  def bind(animal: AnimalJob): UiAction

}
