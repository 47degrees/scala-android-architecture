package com.fortysevendeg.architecture.ui.main.transformations

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.fortysevendeg.architecture.jobs.main.{MainJobs, MainListUiActions}
import com.fortysevendeg.architecture.services.api.Animal
import com.fortysevendeg.architecture.ui.main.adapters.AnimalsAdapter
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.RecyclerViewTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.architecture.R
import macroid.FullDsl._
import macroid._

import scala.language.postfixOps

trait MainListUiActionsImpl
  extends MainListUiActions {

  self : MainBinding =>

  implicit val contextWrapper: ActivityContextWrapper

  val mainJobs: MainJobs

  def init(): Ui[Any] = {
    (contextWrapper.original.get, toolBar) match {
      case (Some(activity: AppCompatActivity), Some(tb)) =>
        activity.setSupportActionBar(tb)
    }
    (recycler
      <~ rvFixedSize
      <~ rvLayoutManager(new GridLayoutManager(contextWrapper.bestAvailable, 2))) ~
      (fabActionButton
        <~ ivSrc(R.drawable.ic_add)
        <~ On.click(Ui(mainJobs.addItem(this))))
  }

  def loadAnimals(data: Seq[Animal]): Ui[Any] =
    recycler <~ rvAdapter(new AnimalsAdapter(data))

  def addItem(): Ui[Any] =
    content <~ vSnackbarLong(R.string.material_list_add_item)

}


