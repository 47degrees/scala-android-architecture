package com.fortysevendeg.architecture.ui.main.transformations

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.fortysevendeg.architecture.jobs.main.{MainJobs, MainListUiActions}
import com.fortysevendeg.architecture.services.api.Animal
import com.fortysevendeg.architecture.ui.main.adapters.AnimalsAdapter
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.RecyclerViewTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.architecture.R
import macroid.FullDsl._
import macroid._
import sarch.UiAction

import scala.language.postfixOps

trait MainListUiActionsImpl
  extends MainListUiActions {

  self : MainBinding =>

  implicit val contextWrapper: ActivityContextWrapper

  implicit val mainJobs: MainJobs

  override def init(): UiAction = UiAction {
    (contextWrapper.original.get, toolBar) match {
      case (Some(activity: AppCompatActivity), Some(tb)) =>
        activity.setSupportActionBar(tb)
      case _ =>
    }
    ((recycler
      <~ rvFixedSize
      <~ rvLayoutManager(new GridLayoutManager(contextWrapper.bestAvailable, 2))) ~
      (fabActionButton
        <~ ivSrc(R.drawable.ic_add)
        <~ On.click(Ui(mainJobs.addItem(this))))).run
  }

  override def loadAnimals(data: Seq[Animal]): UiAction = UiAction {
    (recycler <~ rvAdapter(new AnimalsAdapter(data))).run
  }

  override def addItem(): UiAction = UiAction {
    (content <~ vSnackbarLong(R.string.material_list_add_item)).run
  }

}

