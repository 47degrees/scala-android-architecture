/*
 *  Copyright (C) 2016 47 Degrees, LLC http://47deg.com hello@47deg.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may
 *  not use this file except in compliance with the License. You may obtain
 *  a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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
