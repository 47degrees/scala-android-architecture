package com.fortysevendeg.architecture.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cats.data.Xor
import com.fortysevendeg.architecture.{R, TypedFindView}
import macroid.Contexts
import commons.Service._
import cats.implicits._
import com.fortysevendeg.architecture.ui.main.jobs.MainJobs

import scalaz.concurrent.Task
import scalaz.{-\/, \/-}
import commons.TasksOps._

class MainActivity
  extends AppCompatActivity
  with TypedFindView
  with Contexts[AppCompatActivity] {

  implicit lazy val jobs = new MainJobs(this)

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.material_list_activity)

    val tasks = (jobs.initialize |@| jobs.loadAnimals).tupled

//    Task.fork(tasks.value.or(jobs.showError.value)).runAsync {
//      case -\/(ex) =>
//      case \/-(Xor.Right(response)) =>
//      case \/-(Xor.Left(ex)) =>
//    }

//    Task.fork(tasks.value).runAsync {
//      case -\/(ex) => jobs.showError.value.run
//      case \/-(Xor.Right(response)) =>
//      case \/-(Xor.Left(ex)) => jobs.showError.value.run
//    }

    tasks.value.resolveAsyncService(
      onException = (e) => jobs.showError
    )

  }

}
