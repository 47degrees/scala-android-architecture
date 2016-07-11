package com.fortysevendeg.architecture.ui.main.transformations

import com.fortysevendeg.architecture.jobs.Binding
import com.fortysevendeg.architecture.{TR, TypedFindView}

class MainBinding(fv: TypedFindView)
  extends Binding {

  lazy val content = Option(fv.findView(TR.content))

  lazy val toolBar = Option(fv.findView(TR.toolbar))

  lazy val appBarLayout = Option(fv.findView(TR.app_bar_layout))

  lazy val recycler = Option(fv.findView(TR.recycler))

  lazy val fabActionButton = Option(fv.findView(TR.fab_action_button))

}
