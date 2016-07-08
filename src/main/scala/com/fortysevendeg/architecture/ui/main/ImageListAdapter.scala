package com.fortysevendeg.scala.architecture.ui.main

import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.{LayoutInflater, View, ViewGroup}
import com.fortysevendeg.scala.architecture.R
import macroid.ActivityContextWrapper

case class ImageListAdapter(images: Seq[ImageData])(implicit context: ActivityContextWrapper)
    extends RecyclerView.Adapter[ImageViewHolder]
    with View.OnClickListener {

  override def onCreateViewHolder(parent: ViewGroup, i: Int): ImageViewHolder = {
    val v = LayoutInflater.from(parent.getContext).inflate(R.layout.image_item, parent, false)
    v.setOnClickListener(this)
    new ImageViewHolder(v)
  }

  override def getItemCount: Int = images.size

  override def onBindViewHolder(viewHolder: ImageViewHolder, position: Int): Unit =
    viewHolder.bind(images(position), position).run

  override def onClick(v: View): Unit = {
    val image = images(v.getTag.toString.toInt)
    Snackbar.make(v, image.name, Snackbar.LENGTH_LONG).show()
  }
}

case class ImageData(name: String, url: String)

