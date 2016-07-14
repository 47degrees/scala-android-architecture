package com.fortysevendeg.architecture.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cats.implicits._
import cats.data.Xor
import com.fortysevendeg.architecture.jobs.main.MainJobs
import com.fortysevendeg.architecture.ui.main.transformations._
import com.fortysevendeg.architecture.{R, TypedFindView}
import macroid.Contexts
import sarch.Service._

import scalaz.{-\/, \/-}
import scalaz.concurrent.Task

class MainActivity
  extends AppCompatActivity
  with TypedFindView
  with Contexts[AppCompatActivity] {

  lazy val listActions = new MainBinding(this)
    with MainListUiActionsImpl
    with LoadingUiActionsImpl

  lazy val rowActions = new AnimalHolderBinding(this)
    with AnimalHolderUiActionsImpl

  implicit lazy val jobs = new MainJobs(listActions, rowActions)

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.material_list_activity)

    val tasks = for {
      _ <- jobs.initialize
      _ <- jobs.loadAnimals
    } yield ()

    Task.fork(tasks.value).runAsync {
      case -\/(ex) =>
      case \/-(Xor.Right(response)) =>
      case \/-(Xor.Left(ex)) =>
    }

  }

}
