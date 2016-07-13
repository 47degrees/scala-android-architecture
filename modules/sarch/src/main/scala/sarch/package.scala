import cats.data._

package object sarch {

  type Job[D <: Binding] = Reader[D, Unit]

  trait Binding

}
