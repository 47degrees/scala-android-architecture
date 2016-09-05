/*
 *  Copyright (C) 2016 47 Degrees, LLC http://47deg.com hello@47deg.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may
 *  not use this file except in compliance with the License. You may obtain
 *  a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.fortysevendeg.architecture.ui.main.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.fortysevendeg.architecture.services.api.Animal
import com.fortysevendeg.architecture.ui.commons.AsyncImageTweaks._
import com.fortysevendeg.architecture.{TR, TypedFindView}
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import macroid.FullDsl._
import macroid._

case class AnimalViewHolder(parent: View)(implicit cw: ContextWrapper)
  extends RecyclerView.ViewHolder(parent)
  with TypedFindView {

  lazy val image = Option(findView(TR.image))

  lazy val text = Option(findView(TR.text))

  override protected def findViewById(id: Int): View = parent.findViewById(id)

  def bind(animal: Animal): Unit =
    ((parent <~ On.click(parent <~ vSnackbarLong(animal.name))) ~
      (text <~ tvText(animal.name)) ~
      (image <~ srcImage(animal.url))).run

}
