package com.fortysevendeg.architecture.ui.commons

import commons.TaskService.ServiceException

case class UiException(message: String, cause: Option[Throwable] = None)
  extends RuntimeException(message)
  with ServiceException {
  cause map initCause
}

trait ImplicitsUiExceptions {
  implicit def uiExceptionConverter = (t: Throwable) => UiException(t.getMessage, Option(t))
}
