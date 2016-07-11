package com.fortysevendeg.architecture

import cats.data._

package object jobs {

  type Job[D <: Binding] = Reader[D, Unit]

  trait Binding

}
