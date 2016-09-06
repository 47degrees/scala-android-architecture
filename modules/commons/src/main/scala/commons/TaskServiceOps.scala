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

import cats.syntax.either._
import commons.AppLog._
import commons.TaskService.{ServiceException, TaskService}
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.util.{Either, Failure, Success}

object TaskServiceOps {

  implicit class TaskServiceUi[A](t: TaskService[A]) {

    def resolveAsync[E >: Throwable](
      onResult: A => Unit = a => (),
      onException: E => Unit = (e: Throwable) => ()
    ): Unit = {
      Task.fork(t.value).runAsync { result =>
        result match {
          case Failure(ex) =>
            printErrorTaskMessage("=> EXCEPTION Disjunction <=", ex)
            onException(ex)
          case Success(Right(value)) => onResult(value)
          case Success(Left(ex)) =>
            printErrorTaskMessage(s"=> EXCEPTION Xor Left) <=", ex)
            onException(ex)
        }
      }
    }

    def resolveAsyncService[E >: Throwable](
      onResult: (A) => TaskService[A] = a => TaskService(Task(Either.right(a))),
      onException: (E) => TaskService[A] = (e: ServiceException) => TaskService(Task(Either.left(e)))): Unit = {
      Task.fork(t.value).runAsync { result =>
        result match {
          case Failure(ex) =>
            printErrorTaskMessage("=> EXCEPTION Disjunction <=", ex)
            onException(ex).value.runAsync
          case Success(Right(response)) => onResult(response).value.coeval
          case Success(Left(ex)) =>
            printErrorTaskMessage(s"=> EXCEPTION Xor Left) <=", ex)
            onException(ex).value.runAsync
        }
      }
    }

    def resolve[E >: Throwable](
      onResult: A => Unit = a => (),
      onException: E => Unit = (e: Throwable) => ()): Unit = {
      t.value.map {
        case Right(response) => onResult(response)
        case Left(ex) =>
          printErrorTaskMessage("=> EXCEPTION Xor Left <=", ex)
          onException(ex)
      }.coeval.runAttempt
    }

    def resolveService[E >: Throwable](
      onResult: (A) => TaskService[A] = a => TaskService(Task(Either.right(a))),
      onException: (E) => TaskService[A] = (e: ServiceException) => TaskService(Task(Either.left(e)))): Unit = {
      Task.fork(t.value).map {
        case Right(response) => onResult(response).value.coeval.runAttempt
        case Left(ex) =>
          printErrorTaskMessage("=> EXCEPTION Xor Left <=", ex)
          onException(ex).value.coeval.runAttempt
      }.coeval.runAttempt
    }

    def resolveServiceOr[E >: Throwable](exception: (E) => TaskService[A]) = resolveService(onException = exception)

    def resolveAsyncServiceOr[E >: Throwable](exception: (E) => TaskService[A]) = resolveAsyncService(onException = exception)

  }

}