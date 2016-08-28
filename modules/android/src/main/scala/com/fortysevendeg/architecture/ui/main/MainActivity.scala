package com.fortysevendeg.architecture.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cats.implicits._
import com.fortysevendeg.architecture.ui.main.jobs.MainJobs
import com.fortysevendeg.architecture.{R, TypedFindView}
import commons.TaskService._
import commons.TasksOps._
import macroid.Contexts

class MainActivity
  extends AppCompatActivity
  with TypedFindView
  with Contexts[AppCompatActivity] {

  implicit lazy val jobs = new MainJobs(this)

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.material_list_activity)

    val tasks = (jobs.initialize |@| jobs.loadAnimals).tupled

    tasks.value.resolveOr(_ => jobs.showError)

  }

}
