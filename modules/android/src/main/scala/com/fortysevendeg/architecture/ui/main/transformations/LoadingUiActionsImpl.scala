package com.fortysevendeg.architecture.ui.main.transformations

import com.fortysevendeg.architecture.ui.commons.UiOps._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import commons.Service._
import macroid._

trait LoadingUiActionsImpl {

  self: MainBinding =>

  def showLoading(): Service[Throwable, Unit] =
    ((loading <~ vVisible) ~
      (recycler <~ vGone)).toService

  def showContent(): Service[Throwable, Unit] =
    ((loading <~ vGone) ~
      (recycler <~ vVisible)).toService

}
