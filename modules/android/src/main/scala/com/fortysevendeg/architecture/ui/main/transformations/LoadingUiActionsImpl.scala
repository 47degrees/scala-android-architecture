package com.fortysevendeg.architecture.ui.main.transformations

import com.fortysevendeg.architecture.jobs.main.uiactions.LoadingUiActions
import sarch.UiAction
import com.fortysevendeg.macroid.extras.ViewTweaks._
import macroid._

trait LoadingUiActionsImpl
  extends LoadingUiActions {

  self: MainBinding =>

  override def showLoading(): UiAction = UiAction {
    ((loading <~ vVisible) ~
      (recycler <~ vGone)).run
  }

  override def showContent(): UiAction = UiAction {
    ((loading <~ vGone) ~
      (recycler <~ vVisible)).run
  }

}
