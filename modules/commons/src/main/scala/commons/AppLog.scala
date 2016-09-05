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

package commons

object AppLog {

  val tag = "scala_architecture"

  def printErrorMessage(ex: Throwable, message: Option[String] = None) = {
    try {
      val outputEx = Option(ex.getCause) getOrElse ex
      println(s"$tag - ${message getOrElse errorMessage(outputEx)} $outputEx")
    } catch { case _: Throwable => }
  }

  def printErrorTaskMessage(header: String, ex: Throwable) = {
    try {
      println(s"$tag - $header")
      printErrorMessage(ex, Some(errorMessage(ex)))
    } catch { case _: Throwable => }
  }

  private[this] def errorMessage(ex: Throwable): String =
    Option(ex.getMessage) getOrElse ex.getClass.toString

}
