package com.fortysevendeg.architecture.ui.main.transformations

import com.fortysevendeg.scala.architecture.{TR, TypedFindView}

trait MainBinding {

  self: TypedFindView =>

  lazy val content = Option(findView(TR.content))

  lazy val toolBar = Option(findView(TR.toolbar))

  lazy val appBarLayout = Option(findView(TR.app_bar_layout))

  lazy val recycler = Option(findView(TR.recycler))

  lazy val fabActionButton = Option(findView(TR.fab_action_button))

}
