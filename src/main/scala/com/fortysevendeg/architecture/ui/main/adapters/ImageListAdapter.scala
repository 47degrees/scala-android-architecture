package com.fortysevendeg.architecture.ui.main.adapters

import android.support.v7.widget.RecyclerView
import android.view.{LayoutInflater, ViewGroup}
import com.fortysevendeg.architecture.ui.main.holders.ImageViewHolder
import com.fortysevendeg.scala.architecture.R
import macroid._

case class ImageListAdapter(images: Seq[ImageData])(implicit context: ContextWrapper)
    extends RecyclerView.Adapter[ImageViewHolder] {

  override def onCreateViewHolder(parent: ViewGroup, i: Int): ImageViewHolder = {
    val v = LayoutInflater.from(parent.getContext).inflate(R.layout.image_item, parent, false)
    new ImageViewHolder(v)
  }

  override def getItemCount: Int = images.size

  override def onBindViewHolder(viewHolder: ImageViewHolder, position: Int): Unit =
    viewHolder.bind(images(position)).run

}

case class ImageData(name: String, url: String)

