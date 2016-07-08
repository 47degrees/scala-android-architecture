package com.fortysevendeg.scala.architecture.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fortysevendeg.architecture.jobs.main.MainJobs
import com.fortysevendeg.architecture.ui.main.transformations.{MainBinding, MainListUiActionsImpl}
import com.fortysevendeg.scala.architecture.{R, TypedFindView}
import macroid.Contexts

class MainActivity
  extends AppCompatActivity
  with TypedFindView
  with MainBinding
  with Contexts[AppCompatActivity] {

  lazy val actions = new MainListUiActionsImpl(this)

  val jobs = new MainJobs()

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.material_list_activity)

    toolBar foreach setSupportActionBar

    jobs.loadAnimals(actions)

  }

}
