package com.fortysevendeg.architecture.ui.commons

import cats.data.Xor
import commons.Service
import commons.Service.Service
import macroid.Ui

import scalaz.concurrent.Task

object UiOps {

  implicit class ServiceUi(ui: Ui[Any]) {

    def toService: Service[Throwable, Unit] = Service {
      Task {
        Xor.catchNonFatal {
          ui.run
        }
      }
    }

  }

}
