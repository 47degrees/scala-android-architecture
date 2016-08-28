package com.fortysevendeg.architecture.ui.main.transformations

import com.fortysevendeg.architecture.ui.commons.UiOps._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import commons.TaskService._
import macroid._

trait LoadingUiActionsImpl {

  self: MainBinding =>

  def showLoading(): TaskService[Unit] =
    ((loading <~ vVisible) ~
      (recycler <~ vGone)).toService

  def showContent(): TaskService[Unit] =
    ((loading <~ vGone) ~
      (recycler <~ vVisible)).toService

  def showError(): TaskService[Unit] =
    ((loading <~ vGone) ~
      (recycler <~ vGone)).toService

}
