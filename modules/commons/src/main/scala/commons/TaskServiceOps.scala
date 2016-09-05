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

package commons

import cats.data.Xor
import commons.AppLog._
import commons.TaskService.{ServiceException, TaskService}

import scalaz.{-\/, \/-}
import scalaz.concurrent.Task

object TaskServiceOps {

  implicit class TaskServiceUi[A](t: TaskService[A]) {

    def resolveAsync[E >: Throwable](
      onResult: A => Unit = a => (),
      onException: E => Unit = (e: Throwable) => ()
    ): Unit = {
      Task.fork(t.value).runAsync {
        case -\/(ex) =>
          printErrorTaskMessage("=> EXCEPTION Disjunction <=", ex)
          onException(ex)
        case \/-(Xor.Right(response)) => onResult(response)
        case \/-(Xor.Left(ex)) =>
          printErrorTaskMessage(s"=> EXCEPTION Xor Left) <=", ex)
          onException(ex)
      }
    }

    def resolveAsyncService[E >: Throwable](
      onResult: (A) => TaskService[A] = a => TaskService(Task(Xor.Right(a))),
      onException: (E) => TaskService[A] = (e: ServiceException) => TaskService(Task(Xor.Left(e)))): Unit = {
      Task.fork(t.value).runAsync {
        case -\/(ex) =>
          printErrorTaskMessage("=> EXCEPTION Disjunction <=", ex)
          onException(ex).value.run
        case \/-(Xor.Right(response)) => onResult(response).value.run
        case \/-(Xor.Left(ex)) =>
          printErrorTaskMessage(s"=> EXCEPTION Xor Left) <=", ex)
          onException(ex).value.run
      }
    }

    def resolve[E >: Throwable](
      onResult: A => Unit = a => (),
      onException: E => Unit = (e: Throwable) => ()): Unit = {
      Task.fork(t.value).map {
        case Xor.Right(response) => onResult(response)
        case Xor.Left(ex) =>
          printErrorTaskMessage("=> EXCEPTION Xor Left <=", ex)
          onException(ex)
      }.attemptRun
    }

    def resolveService[E >: Throwable](
      onResult: (A) => TaskService[A] = a => TaskService(Task(Xor.Right(a))),
      onException: (E) => TaskService[A] = (e: ServiceException) => TaskService(Task(Xor.Left(e)))): Unit = {
      Task.fork(t.value).map {
        case Xor.Right(response) => onResult(response).value.run
        case Xor.Left(ex) =>
          printErrorTaskMessage("=> EXCEPTION Xor Left <=", ex)
          onException(ex).value.run
      }.attemptRun
    }

    def resolveServiceOr[E >: Throwable](exception: (E) => TaskService[A]) = resolveService(onException = exception)

    def resolveAsyncServiceOr[E >: Throwable](exception: (E) => TaskService[A]) = resolveAsyncService(onException = exception)

  }

}