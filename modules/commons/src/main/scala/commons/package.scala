import cats.{Functor, Monad}
import cats.data._

import scalaz.concurrent.Task
import scala.language.{higherKinds, implicitConversions}

package object commons {

  object TaskService {

    implicit val taskFunctor = new Functor[Task] {
      override def map[A, B](fa: Task[A])(f: (A) => B): Task[B] = fa.map(f)
    }

    implicit val taskMonad = new Monad[Task] {
      override def flatMap[A, B](fa: Task[A])(f: (A) => Task[B]): Task[B] = fa.flatMap(f)
      override def pure[A](x: A): Task[A] = Task(x)
    }

    type TaskService[A] = XorT[Task, ServiceException, A]

    trait ServiceException extends RuntimeException {
      def message: String
      def cause: Option[Throwable]
    }

    def apply[A](f: Task[ServiceException Xor A]) : TaskService[A] = {
      XorT[Task, ServiceException, A](f)
    }

  }

}
