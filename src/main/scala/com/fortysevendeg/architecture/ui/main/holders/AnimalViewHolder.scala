package com.fortysevendeg.architecture.ui.main.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.fortysevendeg.architecture.services.api.Animal
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.architecture.ui.commons.AsyncImageTweaks._
import com.fortysevendeg.scala.architecture.{TR, TypedFindView}
import macroid._
import macroid.FullDsl._

case class AnimalViewHolder(parent: View)
  extends RecyclerView.ViewHolder(parent)
  with TypedFindView {

  lazy val image = Option(findView(TR.image))
  lazy val text = Option(findView(TR.text))

  override protected def findViewById(id: Int): View = parent.findViewById(id)

  def bind(animal: Animal)(implicit contextWrapper: ContextWrapper): Ui[_] =
    (parent <~ On.click(parent <~ vSnackbarLong(animal.name))) ~
      (image <~ srcImage(animal.url)) ~
      (text <~ tvText(animal.name))

}
