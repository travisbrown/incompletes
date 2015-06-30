package io.travisbrown.incompletes

import org.scalatest.{Matchers, FlatSpec}
import org.scalatest.prop.Checkers

class IncompletesSpec extends FlatSpec with Matchers with Checkers {
  case class Foo(i: Int, s: String, c: Char, x: Symbol)
}
