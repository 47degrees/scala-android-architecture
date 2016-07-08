package com.fortysevendeg.scala.architecture.ui.main

import android.graphics.Color
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.fortysevendeg.architecture.jobs.main.MainListUiActions
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.RecyclerViewTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.scala.architecture.ui.components.IconTypes._
import com.fortysevendeg.scala.architecture.ui.components.PathMorphDrawable
import com.fortysevendeg.scala.architecture.{R, TR, TypedFindView}
import macroid.FullDsl._
import macroid._

import scala.language.postfixOps

trait MainListUiActionsImpl
  extends MainListUiActions {

  self: TypedFindView with Contexts[AppCompatActivity] =>

  lazy val content = Option(findView(TR.content))

  lazy val toolBar = Option(findView(TR.toolbar))

  lazy val appBarLayout = Option(findView(TR.app_bar_layout))

  lazy val recycler = Option(findView(TR.recycler))

  lazy val fabActionButton = Option(findView(TR.fab_action_button))

  def loadAnimals(data: Seq[ImageData]): Ui[Any] =
    initRecycler(data) ~ initFabButton

  private[this] def initRecycler(data: Seq[ImageData]): Ui[_] =
    (recycler
        <~ rvAdapter(new ImageListAdapter(data))
        <~ rvFixedSize
        <~ rvLayoutManager(new GridLayoutManager(activityContextWrapper.bestAvailable, 2)))

  private[this] def initFabButton: Ui[_] =
    (fabActionButton
        <~ ivSrc(fabDrawable)
        <~ On.click {
      Ui {
        content foreach (Snackbar.make(_, resGetString(R.string.material_list_add_item), Snackbar.LENGTH_LONG).show())
      }
    })

  private[this] def fabDrawable = new PathMorphDrawable(
    defaultIcon = ADD,
    defaultStroke = resGetDimensionPixelSize(R.dimen.circular_reveal_fab_stroke),
    defaultColor = Color.WHITE
  )

}


