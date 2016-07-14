package com.fortysevendeg.architecture.ui.main.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.fortysevendeg.architecture.jobs.main.AnimalJob
import com.fortysevendeg.architecture.ui.commons.AsyncImageTweaks._
import com.fortysevendeg.architecture.{TR, TypedFindView}
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import macroid.FullDsl._
import macroid._

case class AnimalViewHolder(parent: View)(implicit cw: ContextWrapper)
  extends RecyclerView.ViewHolder(parent)
  with TypedFindView {

  lazy val image = Option(findView(TR.image))

  lazy val text = Option(findView(TR.text))

  override protected def findViewById(id: Int): View = parent.findViewById(id)

  def bind(animal: AnimalJob): Unit =
    ((parent <~ On.click(parent <~ vSnackbarLong(animal.name))) ~
      (text <~ tvText(animal.name)) ~
      (image <~ srcImage(animal.url))).run

}
