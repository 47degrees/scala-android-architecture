package com.fortysevendeg.architecture.ui.main.transformations

import com.fortysevendeg.scala.architecture.{TR, TypedFindView}

class AnimalHolderBinding(fv: TypedFindView) {

  lazy val parent = Option(fv.findView(TR.content))

  lazy val image = Option(fv.findView(TR.image))

  lazy val text = Option(fv.findView(TR.text))

}
