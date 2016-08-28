package commons

import cats.data.Xor
import commons.TaskService.{ServiceException, TaskService}
import sarch.AppLog._

import scalaz.concurrent.Task
import scalaz.{-\/, \/-}

object TasksOps {

  implicit class TaskResultUI[E <: Throwable, A](t: Task[Xor[E, A]]) {

    def resolveAsync[E >: Throwable](
      onResult: A => Unit = a => (),
      onException: E => Unit = (e: Throwable) => ()
      ): Unit = {
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

    def resolveAsyncService[E >: Throwable](
      onResult: (A) => TaskService[A] = a => TaskService(Task(Xor.Right(a))),
      onException: (E) => TaskService[A] = (e: ServiceException) => TaskService(Task(Xor.Left(e)))): Unit = {
      Task.fork(t).runAsync {
        case -\/(ex) =>
          printErrorTaskMessage("=> EXCEPTION Disjunction <=", ex)
          onException(ex).value.run
        case \/-(Xor.Right(response)) => onResult(response).value.run
        case \/-(Xor.Left(ex)) =>
          printErrorTaskMessage(s"=> EXCEPTION Xor Left) <=", ex)
          onException(ex).value.run
      }
    }

    def resolveOr[E >: Throwable](exception: (E) => TaskService[A]) = resolveAsyncService(onException = exception)

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

    def resolveService[E >: Throwable](
      onResult: (A) => TaskService[A] = a => TaskService(Task(Xor.Right(a))),
      onException: (E) => TaskService[A] = (e: ServiceException) => TaskService(Task(Xor.Left(e)))): Unit = {
      Task.fork(t).map {
        case Xor.Right(response) => onResult(response).value.run
        case Xor.Left(ex) =>
          printErrorTaskMessage("=> EXCEPTION Xor Left <=", ex)
          onException(ex).value.run
      }.attemptRun
    }

  }

}
