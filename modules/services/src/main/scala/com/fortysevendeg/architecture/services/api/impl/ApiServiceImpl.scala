package com.fortysevendeg.architecture.services.api.impl

import cats.data.Xor
import com.fortysevendeg.architecture.services.api.{Animal, ApiService}
import commons._
import commons.Service._

import scalaz.concurrent.Task

class ApiServiceImpl
  extends ApiService {

  override def getAnimals(simulateFail: Boolean = false): Service[Throwable, Seq[Animal]] =
    Service {
      Task {
        Xor.catchNonFatal {
          Thread.sleep(1500)
          if (simulateFail) throw new RuntimeException
          1 to 10 map { i =>
            Animal(s"Item $i", s"http://lorempixel.com/500/500/animals/$i")
          }
        }
      }
    }

}
