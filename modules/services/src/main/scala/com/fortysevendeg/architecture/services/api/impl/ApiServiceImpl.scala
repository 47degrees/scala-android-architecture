package com.fortysevendeg.architecture.services.api.impl

import com.fortysevendeg.architecture.services.api.{Animal, ApiService, ApiServiceException, ImplicitsApiServiceExceptions}
import commons._
import commons.TaskService._

import scalaz.concurrent.Task

class ApiServiceImpl
  extends ApiService
  with ImplicitsApiServiceExceptions {

  override def getAnimals(simulateFail: Boolean = false): TaskService[Seq[Animal]] =
    TaskService {
      Task {
        XorCatchAll[ApiServiceException] {
          Thread.sleep(1500)
          if (simulateFail) throw new RuntimeException
          1 to 10 map { i =>
            Animal(s"Item $i", s"http://lorempixel.com/500/500/animals/$i")
          }
        }
      }
    }

}
