package com.fortysevendeg.architecture.ui.main.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.architecture.ui.commons.AsyncImageTweaks._
import com.fortysevendeg.scala.architecture.ui.main.ImageData
import com.fortysevendeg.scala.architecture.{TR, TypedFindView}
import macroid._

case class ImageViewHolder(parent: View)
    extends RecyclerView.ViewHolder(parent)
    with TypedFindView {

  lazy val image = Option(findView(TR.image))
  lazy val text = Option(findView(TR.text))

  override protected def findViewById(id: Int): View = parent.findViewById(id)

  def bind(imageData: ImageData, position: Int)(implicit contextWrapper: ActivityContextWrapper): Ui[_] =
    (parent <~ vTag(position.toString)) ~
        (image <~ srcImage(imageData.url)) ~
        (text <~ tvText(imageData.name))

}
