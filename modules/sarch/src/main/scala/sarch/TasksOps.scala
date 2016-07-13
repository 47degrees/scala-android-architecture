package sarch

import cats.data.Xor
import sarch.AppLog._

import scalaz.concurrent.Task
import scalaz.{-\/, \/-}

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

    def resolveAsyncUiAction[E >: Throwable](
      onResult: (A) => UiAction = a => UiAction.nop,
      onException: (E) => UiAction = (e: Throwable) => UiAction.nop,
      onPreTask: () => UiAction = () => UiAction.nop): Unit = {
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

    def resolveUiAction[E >: Throwable](
     onResult: (A) => UiAction = a => UiAction.nop,
     onException: (E) => UiAction = (e: Throwable) => UiAction.nop): Unit = {
      Task.fork(t).map {
        case Xor.Right(response) => onResult(response).run
        case Xor.Left(ex) =>
          printErrorTaskMessage("=> EXCEPTION Xor Left <=", ex)
          onException(ex).run
      }.attemptRun
    }

  }

}
