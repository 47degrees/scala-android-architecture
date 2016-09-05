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

package com.fortysevendeg.architecture.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cats.implicits._
import com.fortysevendeg.architecture.ui.main.jobs.{MainDom, MainJobs, MainListUiActions}
import com.fortysevendeg.architecture.{R, TypedFindView}
import commons.TaskService._
import commons.TaskServiceOps._
import macroid.Contexts

class MainActivity
  extends AppCompatActivity
  with TypedFindView
  with Contexts[AppCompatActivity] {

  lazy val dom = MainDom(this)

  lazy val ui = MainListUiActions(dom)

  lazy val jobs = new MainJobs(ui)

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.material_list_activity)

    val tasks = (jobs.initialize |@| jobs.loadAnimals).tupled

    tasks.resolveServiceOr(_ => jobs.showError)

  }

}
