package com.fortysevendeg.architecture.ui.main.transformations

import com.fortysevendeg.architecture.jobs.main.AnimalHolderUiActions
import com.fortysevendeg.architecture.services.api.Animal
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.architecture.ui.commons.AsyncImageTweaks._
import macroid.FullDsl._
import macroid._

trait AnimalHolderUiActionsImpl
  extends AnimalHolderUiActions {

  self: AnimalHolderBinding =>

  implicit val contextWrapper: ContextWrapper

  override def bind(animal: Animal): Ui[Any] =
    (parent <~ On.click(parent <~ vSnackbarLong(animal.name))) ~
      (text <~ tvText(animal.name)) //~
  //(image <~ srcImage(animal.url))

}
