package com.fortysevendeg.architecture.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cats.data.Xor
import com.fortysevendeg.architecture.ui.main.transformations._
import com.fortysevendeg.architecture.{R, TypedFindView}
import macroid.Contexts
import commons.Service._
import cats.implicits._
import com.fortysevendeg.architecture.ui.main.jobs.MainJobs

import scalaz.concurrent.Task
import scalaz.{-\/, \/-}

class MainActivity
  extends AppCompatActivity
  with TypedFindView
  with Contexts[AppCompatActivity] {

  lazy val listActions = new MainBinding(this)
    with MainListUiActionsImpl
    with LoadingUiActionsImpl

  implicit lazy val jobs = new MainJobs(listActions)

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.material_list_activity)

    val tasks = (jobs.initialize |@| jobs.loadAnimals).tupled

    Task.fork(tasks.value).runAsync {
      case -\/(ex) =>
      case \/-(Xor.Right(response)) =>
      case \/-(Xor.Left(ex)) =>
    }

  }

}
