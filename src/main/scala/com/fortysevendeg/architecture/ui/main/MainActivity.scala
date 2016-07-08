package com.fortysevendeg.scala.architecture.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.fortysevendeg.architecture.jobs.main.MainJobs
import com.fortysevendeg.scala.architecture.{R, TypedFindView}
import macroid.Contexts

class MainActivity
  extends AppCompatActivity
  with TypedFindView
  with MainListUiActionsImpl
  with Contexts[AppCompatActivity] {

  val jobs = new MainJobs(this)

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.material_list_activity)

    toolBar foreach setSupportActionBar

    jobs.loadAnimals()

  }

}
