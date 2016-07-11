package com.fortysevendeg.architecture.ui.commons

import cats.data.Xor
import macroid.Ui

import scalaz.concurrent.Task
import scalaz.{-\/, \/-}
import AppLog._

object TasksOps {

  implicit class TaskResultUI[E <: Exception, A](t: Task[Xor[E, A]]) {

    def resolveAsync[E >: Throwable](
      onResult: A => Unit = a => (),
      onException: E => Unit = (e: Throwable) => (),
      onPreTask: () => Unit = () => ()
      ): Unit = {
      onPreTask()
      Task.fork(t).runAsync {
        case -\/(ex) =>
          printErrorTaskMessage("=> EXCEPTION Disjunction <=", ex)
          onException(ex)
        case \/-(Xor.Right(response)) => onResult(response)
        case \/-(Xor.Left(ex)) =>
          printErrorTaskMessage(s"=> EXCEPTION Xor Left) <=", ex)
          onException(ex)
      }
    }

    def resolveAsyncUi[E >: Throwable](
      onResult: (A) => Ui[_] = a => Ui.nop,
      onException: (E) => Ui[_] = (e: Throwable) => Ui.nop,
      onPreTask: () => Ui[_] = () => Ui.nop): Unit = {
      onPreTask().run
      Task.fork(t).runAsync {
        case -\/(ex) =>
          printErrorTaskMessage("=> EXCEPTION Disjunction <=", ex)
          onException(ex).run
        case \/-(Xor.Right(response)) => onResult(response).run
        case \/-(Xor.Left(ex)) =>
          printErrorTaskMessage(s"=> EXCEPTION Xor Left) <=", ex)
          onException(ex).run
      }
    }

    def resolve[E >: Throwable](
      onResult: A => Unit = a => (),
      onException: E => Unit = (e: Throwable) => ()): Unit = {
      Task.fork(t).map {
        case Xor.Right(response) => onResult(response)
        case Xor.Left(ex) =>
          printErrorTaskMessage("=> EXCEPTION Xor Left <=", ex)
          onException(ex)
      }.attemptRun
    }

    def resolveUi[E >: Throwable](
      onResult: (A) => Ui[_] = a => Ui.nop,
      onException: (E) => Ui[_] = (e: Throwable) => Ui.nop): Unit = {
      Task.fork(t).map {
        case Xor.Right(response) => onResult(response).run
        case Xor.Left(ex) =>
          printErrorTaskMessage("=> EXCEPTION Xor Left <=", ex)
          onException(ex).run
      }.attemptRun
    }

  }

}
