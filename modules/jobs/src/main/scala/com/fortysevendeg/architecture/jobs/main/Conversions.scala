package com.fortysevendeg.architecture.jobs.main

import com.fortysevendeg.architecture.services.api.Animal

object Conversions {

  def toAnimalJob(animal: Animal): AnimalJob =
    AnimalJob(
      name = animal.name,
      url = animal.url)

}
