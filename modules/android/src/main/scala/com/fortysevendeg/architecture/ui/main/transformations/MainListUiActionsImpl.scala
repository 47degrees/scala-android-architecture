package com.fortysevendeg.architecture.ui.main.transformations

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.fortysevendeg.architecture.R
import com.fortysevendeg.architecture.services.api.Animal
import com.fortysevendeg.architecture.ui.commons.UiOps._
import com.fortysevendeg.architecture.ui.main.adapters.AnimalsAdapter
import com.fortysevendeg.architecture.ui.main.jobs.MainJobs
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.RecyclerViewTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import commons.Service._
import macroid.FullDsl._
import macroid._

import scala.language.postfixOps
import scalaz.concurrent.Task

trait MainListUiActionsImpl {

  self : MainBinding =>

  def init(mainJobs: MainJobs): Service[Throwable, Unit] =
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
        <~ On.click(
        Ui {
          Task.fork(mainJobs.addItem.value).run
        }))).toService

  def loadAnimals(data: Seq[Animal]): Service[Throwable, Unit] =
    (recycler <~ rvAdapter(new AnimalsAdapter(data))).toService

  def addItem(): Service[Throwable, Unit] =
    (content <~ vSnackbarLong(R.string.material_list_add_item)).toService

}


