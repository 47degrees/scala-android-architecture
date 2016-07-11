package com.fortysevendeg.architecture.jobs

import com.fortysevendeg.architecture.services.TestServices

case class TestJob(s: String)

object TestJob {
  val t = TestServices("")
}