package com.fortysevendeg.architecture.services.api.impl

import cats.data.Xor
import com.fortysevendeg.architecture.services.api.{Animal, ApiService}
import scalaz.concurrent.Task

class ApiServiceImpl
  extends ApiService {

  override def getAnimals: Task[Exception Xor Seq[Animal]] =
    Task {
      Xor.right {
        Thread.sleep(1500)
        1 to 10 map { i =>
          Animal(s"Item $i", s"http://lorempixel.com/500/500/animals/$i")
        }
      }
    }

}
