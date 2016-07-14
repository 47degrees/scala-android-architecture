package com.fortysevendeg.architecture.ui.main.transformations

import com.fortysevendeg.architecture.jobs.main.AnimalJob
import com.fortysevendeg.architecture.jobs.main.uiactions.AnimalHolderUiActions
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.architecture.ui.commons.AsyncImageTweaks._
import macroid.FullDsl._
import macroid._
import sarch.UiAction

trait AnimalHolderUiActionsImpl
  extends AnimalHolderUiActions {

  self: AnimalHolderBinding =>

  override def bind(animal: AnimalJob): UiAction = UiAction {
    ((parent <~ On.click(parent <~ vSnackbarLong(animal.name))) ~
      (text <~ tvText(animal.name)) ~
      (image <~ srcImage(animal.url))).run
  }

}
