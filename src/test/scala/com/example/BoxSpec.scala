package com.example

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class BoxSpec extends AnyFlatSpec with Matchers {

  "isBlue" should "Return true" in {
    val box1 = new Box(Color.Blue);
    val box0 = new Box();
    assert(box0.isBlue());
    assert(box1.isBlue());
  }
}
