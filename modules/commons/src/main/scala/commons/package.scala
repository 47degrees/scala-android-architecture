import cats.{Functor, Monad}
import cats.data._

import scala.reflect.ClassTag
import scalaz.concurrent.Task
import scala.language.{higherKinds, implicitConversions}

package object commons {

  object Service {

    implicit val taskFunctor = new Functor[Task] {
      override def map[A, B](fa: Task[A])(f: (A) => B): Task[B] = fa.map(f)
    }

    implicit val taskMonad = new Monad[Task] {
      override def flatMap[A, B](fa: Task[A])(f: (A) => Task[B]): Task[B] = fa.flatMap(f)
      override def pure[A](x: A): Task[A] = Task(x)
    }

    type Service[Ex <: Throwable, Val] = XorT[Task, Ex, Val]

    def apply[Ex <: Throwable : ClassTag, Val](f: Task[Ex Xor Val]) : Service[Ex, Val] = {
      XorT[Task, Ex, Val](f)
    }

  }

}
