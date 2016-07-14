package com.fortysevendeg.architecture.ui.main.transformations

import sarch.Binding
import com.fortysevendeg.architecture.{TR, TypedFindView}
import macroid.ContextWrapper

class AnimalHolderBinding(fv: TypedFindView)(implicit val contextWrapper: ContextWrapper)
  extends Binding {

  lazy val parent = Option(fv.findView(TR.content))

  lazy val image = Option(fv.findView(TR.image))

  lazy val text = Option(fv.findView(TR.text))

}
