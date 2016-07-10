package com.fortysevendeg.architecture.ui.main.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.fortysevendeg.architecture.ui.main.transformations.{AnimalHolderBinding, AnimalHolderUiActionsImpl}
import com.fortysevendeg.scala.architecture.TypedFindView
import macroid._

case class AnimalViewHolder(parent: View)(implicit cw: ContextWrapper)
  extends RecyclerView.ViewHolder(parent)
  with TypedFindView {

  lazy val actions = new AnimalHolderBinding(this) with AnimalHolderUiActionsImpl {
    override implicit val contextWrapper: ContextWrapper = cw
  }

  override protected def findViewById(id: Int): View = parent.findViewById(id)

}
