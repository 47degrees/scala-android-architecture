package com.fortysevendeg.architecture.ui.main.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.fortysevendeg.architecture.ui.main.adapters.ImageData
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.architecture.ui.commons.AsyncImageTweaks._
import com.fortysevendeg.scala.architecture.{TR, TypedFindView}
import macroid._
import macroid.FullDsl._

case class ImageViewHolder(parent: View)
  extends RecyclerView.ViewHolder(parent)
  with TypedFindView {

  lazy val image = Option(findView(TR.image))
  lazy val text = Option(findView(TR.text))

  override protected def findViewById(id: Int): View = parent.findViewById(id)

  def bind(imageData: ImageData)(implicit contextWrapper: ContextWrapper): Ui[_] =
    (parent <~ On.click(parent <~ vSnackbarLong(imageData.name))) ~
      (image <~ srcImage(imageData.url)) ~
      (text <~ tvText(imageData.name))

}
