package com.fortysevendeg.architecture.ui.main.adapters

import android.support.v7.widget.RecyclerView
import android.view.{LayoutInflater, ViewGroup}
import com.fortysevendeg.architecture.jobs.main.MainJobs
import com.fortysevendeg.architecture.services.api.Animal
import com.fortysevendeg.architecture.ui.main.holders.AnimalViewHolder
import com.fortysevendeg.scala.architecture.R
import macroid._

case class AnimalsAdapter(animals: Seq[Animal])(implicit mainJobs: MainJobs, context: ContextWrapper)
    extends RecyclerView.Adapter[AnimalViewHolder] {

  override def onCreateViewHolder(parent: ViewGroup, i: Int): AnimalViewHolder = {
    val v = LayoutInflater.from(parent.getContext).inflate(R.layout.image_item, parent, false)
    new AnimalViewHolder(v)
  }

  override def getItemCount: Int = animals.size

  override def onBindViewHolder(viewHolder: AnimalViewHolder, position: Int): Unit =
    mainJobs.bindAnimal(animals(position))(viewHolder.actions)

}

