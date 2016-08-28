package com.fortysevendeg.architecture.services.api

import commons.TaskService.ServiceException

case class ApiServiceException(message: String, cause: Option[Throwable] = None)
  extends RuntimeException(message)
  with ServiceException {
  cause map initCause
}

trait ImplicitsApiServiceExceptions {
  implicit def apiServiceExceptionConverter = (t: Throwable) => ApiServiceException(t.getMessage, Option(t))
}
