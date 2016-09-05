package com.fortysevendeg.architecture.ui.main.jobs

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

case class MainListUiActions(dom: MainDom)(implicit contextWrapper: ContextWrapper) {

  def init(jobs: MainJobs): TaskService[Unit] =
    (Ui {
      (contextWrapper.original.get, dom.toolBar) match {
        case (Some(activity: AppCompatActivity), Some(tb)) =>
          activity.setSupportActionBar(tb)
        case _ =>
      }
    } ~
      (dom.recycler
        <~ rvFixedSize
        <~ rvLayoutManager(new GridLayoutManager(contextWrapper.bestAvailable, 2))) ~
      (dom.fabActionButton
        <~ ivSrc(R.drawable.ic_add)
        <~ On.click(Ui(jobs.addItem().value.run)))).toService

  def loadAnimals(data: Seq[Animal]): TaskService[Unit] =
    (dom.recycler <~ rvAdapter(AnimalsAdapter(data))).toService

  def addItem(): TaskService[Unit] =
    (dom.content <~ vSnackbarLong(R.string.material_list_add_item)).toService

  def displayError(): TaskService[Unit] =
    (dom.content <~ vSnackbarIndefinite(R.string.material_list_error)).toService

  def showLoading(): TaskService[Unit] =
    ((dom.loading <~ vVisible) ~
      (dom.recycler <~ vGone)).toService

  def showContent(): TaskService[Unit] =
    ((dom.loading <~ vGone) ~
      (dom.recycler <~ vVisible)).toService

  def showError(): TaskService[Unit] =
    ((dom.loading <~ vGone) ~
      (dom.recycler <~ vGone)).toService

}


