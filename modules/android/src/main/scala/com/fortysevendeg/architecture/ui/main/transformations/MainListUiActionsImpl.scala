package com.fortysevendeg.architecture.ui.main.transformations

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.fortysevendeg.architecture.R
import com.fortysevendeg.architecture.services.api.Animal
import com.fortysevendeg.architecture.ui.commons.UiOps._
import com.fortysevendeg.architecture.ui.main.adapters.AnimalsAdapter
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.RecyclerViewTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import commons.TaskService._
import macroid.FullDsl._
import macroid._

import scala.language.postfixOps

trait MainListUiActionsImpl {

  self: MainBinding =>

  def init(): TaskService[Unit] =
    (Ui {
      (contextWrapper.original.get, toolBar) match {
        case (Some(activity: AppCompatActivity), Some(tb)) =>
          activity.setSupportActionBar(tb)
        case _ =>
      }
    } ~
      (recycler
        <~ rvFixedSize
        <~ rvLayoutManager(new GridLayoutManager(contextWrapper.bestAvailable, 2))) ~
      (fabActionButton
        <~ ivSrc(R.drawable.ic_add)
        <~ On.click(Ui(jobs.addItem().value.run)))).toService

  def loadAnimals(data: Seq[Animal]): TaskService[Unit] =
    (recycler <~ rvAdapter(new AnimalsAdapter(data))).toService

  def addItem(): TaskService[Unit] =
    (content <~ vSnackbarLong(R.string.material_list_add_item)).toService

  def displayError(): TaskService[Unit] =
    (content <~ vSnackbarIndefinite(R.string.material_list_error)).toService

}


