/*
 * Copyright (C) 2015 47 Degrees, LLC http://47deg.com hello@47deg.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may
 *  not use this file except in compliance with the License. You may obtain
 *  a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.fortysevendeg.architecture.ui.commons

import android.widget.ImageView
import com.fortysevendeg.macroid.extras.DeviceVersion._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.architecture.ui.components.CircularTransformation

//import com.squareup.picasso.Picasso
import macroid.{ContextWrapper, Tweak}

import scala.language.postfixOps

object AsyncImageTweaks {
  type W = ImageView

  //  def roundedImage(url: String,
  //        placeHolder: Int,
  //        size: Int)(implicit context: ContextWrapper) = CurrentVersion match {
  //    case sdk if sdk >= Lollipop =>
  //      srcImage(url, placeHolder) + vCircleOutlineProvider(0)
  //    case _ =>
  //      roundedImageTweak(url, placeHolder, size)
  //  }
  //
  //  private def roundedImageTweak(
  //      url: String,
  //      placeHolder: Int,
  //      size: Int
  //      )(implicit context: ContextWrapper): Tweak[W] = Tweak[W](
  //    imageView => {
  //      Picasso.`with`(context.getOriginal)
  //          .load(url)
  //          .transform(new CircularTransformation(size))
  //          .placeholder(placeHolder)
  //          .into(imageView)
  //    }
  //  )
  //
  //  def srcImage(
  //      url: String,
  //      placeHolder: Int
  //      )(implicit context: ContextWrapper): Tweak[W] = Tweak[W](
  //    imageView => {
  //      Picasso.`with`(context.getOriginal)
  //          .load(url)
  //          .placeholder(placeHolder)
  //          .into(imageView)
  //    }
  //  )
  //
  //  def srcImage(url: String)(implicit context: ContextWrapper): Tweak[W] = Tweak[W](
  //    imageView => {
  //      Picasso.`with`(context.getOriginal)
  //          .load(url)
  //          .into(imageView)
  //    }
  //  )
}

