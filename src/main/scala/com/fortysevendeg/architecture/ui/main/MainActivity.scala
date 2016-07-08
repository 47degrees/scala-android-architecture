package com.fortysevendeg.scala.architecture.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.MenuItem
import cats.data.Xor
import com.fortysevendeg.architecture.services.api.impl.ApiServiceImpl
import com.fortysevendeg.macroid.extras.RecyclerViewTweaks._
import com.fortysevendeg.scala.architecture.{R, TypedFindView}
import macroid.{Contexts, Ui}
import macroid.FullDsl._

import scalaz.{-\/, \/-}
import scalaz.concurrent.Task

class MainActivity
  extends AppCompatActivity
  with TypedFindView
  with MainComposer
  with Contexts[AppCompatActivity] {

  val apiService = new ApiServiceImpl

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.material_list_activity)

    toolBar foreach setSupportActionBar

    Task.fork(apiService.getAnimals).runAsync {
      case -\/(ex) =>
      case \/-(Xor.Left(ex)) =>
      case \/-(Xor.Right(data)) =>
        val imageData = data map (d => ImageData(d.name, d.url))
        composition(imageData).run
    }


  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = item.getItemId match {
    case android.R.id.home =>
      finish()
      false
    case _ => super.onOptionsItemSelected(item)
  }
}
