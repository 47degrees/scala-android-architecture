package com.fortysevendeg.architecture.jobs.main

import com.fortysevendeg.architecture.services.api.Animal
import macroid.Ui

trait AnimalHolderUiActions {

  def bind(animal: Animal): Ui[Any]

}
