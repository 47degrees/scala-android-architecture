/*
 *  Copyright (C) 2016 47 Degrees, LLC http://47deg.com hello@47deg.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may
 *  not use this file except in compliance with the License. You may obtain
 *  a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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
import commons.TaskServiceOps._
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
        <~ On.click(Ui(jobs.addItem().resolveAsync())))).toService

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


