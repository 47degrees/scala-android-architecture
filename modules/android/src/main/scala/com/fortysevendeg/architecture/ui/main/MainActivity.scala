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
