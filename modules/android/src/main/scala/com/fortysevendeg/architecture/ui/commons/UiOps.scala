package com.fortysevendeg.architecture.ui.commons

import commons.TaskService.TaskService
import commons.{TaskService, XorCatchAll}
import macroid.Ui

import scalaz.concurrent.Task

object UiOps extends ImplicitsUiExceptions {

  implicit class ServiceUi(ui: Ui[Any]) {

    def toService: TaskService[Unit] = TaskService {
      Task(XorCatchAll[UiException](ui.run))
    }

  }

}
