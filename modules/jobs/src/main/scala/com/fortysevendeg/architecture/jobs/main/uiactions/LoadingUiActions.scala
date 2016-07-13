package com.fortysevendeg.architecture.jobs.main.uiactions

import sarch.UiAction

trait LoadingUiActions {

  def showLoading(): UiAction

  def showContent(): UiAction

}
