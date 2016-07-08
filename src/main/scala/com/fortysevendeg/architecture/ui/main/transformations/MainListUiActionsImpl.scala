package com.fortysevendeg.architecture.ui.main.transformations

import android.graphics.Color
import android.support.v7.widget.GridLayoutManager
import com.fortysevendeg.architecture.jobs.main.MainListUiActions
import com.fortysevendeg.architecture.services.api.Animal
import com.fortysevendeg.architecture.ui.main.adapters.AnimalsAdapter
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.RecyclerViewTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.architecture.R
import com.fortysevendeg.scala.architecture.ui.components.IconTypes._
import com.fortysevendeg.scala.architecture.ui.components.PathMorphDrawable
import macroid.FullDsl._
import macroid._

import scala.language.postfixOps

class MainListUiActionsImpl(binding: MainBinding)(implicit contextWrapper: ContextWrapper)
  extends MainListUiActions {

  def loadAnimals(data: Seq[Animal]): Ui[Any] =
    (binding.recycler
      <~ rvAdapter(new AnimalsAdapter(data))
      <~ rvFixedSize
      <~ rvLayoutManager(new GridLayoutManager(contextWrapper.bestAvailable, 2))) ~
      (binding.fabActionButton
        <~ ivSrc(fabDrawable)
        <~ On.click(binding.content <~ vSnackbarLong(R.string.material_list_add_item)))

  private[this] def fabDrawable = new PathMorphDrawable(
    defaultIcon = ADD,
    defaultStroke = resGetDimensionPixelSize(R.dimen.circular_reveal_fab_stroke),
    defaultColor = Color.WHITE
  )

}


