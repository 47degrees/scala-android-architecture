package com.fortysevendeg.architecture.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fortysevendeg.architecture.jobs.TestJob
import com.fortysevendeg.architecture.jobs.main.MainJobs
import com.fortysevendeg.architecture.ui.main.transformations.{MainBinding, MainListUiActionsImpl}
import com.fortysevendeg.architecture.{R, TypedFindView}
import macroid.{ActivityContextWrapper, Contexts}

class MainActivity
  extends AppCompatActivity
  with TypedFindView
  with Contexts[AppCompatActivity] {

  val jobs = new MainJobs()

  val s = TestJob("")

  lazy val actions = new MainBinding(this) with MainListUiActionsImpl {
    override implicit val contextWrapper: ActivityContextWrapper = activityContextWrapper
    override implicit val mainJobs: MainJobs = jobs
  }

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.material_list_activity)

    jobs.initialize(actions)

  }

}