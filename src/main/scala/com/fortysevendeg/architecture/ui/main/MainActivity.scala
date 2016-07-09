package com.fortysevendeg.scala.architecture.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fortysevendeg.architecture.jobs.main.MainJobs
import com.fortysevendeg.architecture.ui.main.transformations.{MainBinding, MainListUiActionsImpl}
import com.fortysevendeg.scala.architecture.{R, TR, TypedFindView}
import macroid.{ActivityContextWrapper, ContextWrapper, Contexts}

class MainActivity
  extends AppCompatActivity
  with TypedFindView
  with Contexts[AppCompatActivity] {

  lazy val actions = new MainBinding(this) with MainListUiActionsImpl {
    override implicit val contextWrapper: ActivityContextWrapper = activityContextWrapper
  }

  val jobs = new MainJobs()

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.material_list_activity)

    setSupportActionBar(findView(TR.toolbar))

    jobs.initialize(actions)

  }

}
