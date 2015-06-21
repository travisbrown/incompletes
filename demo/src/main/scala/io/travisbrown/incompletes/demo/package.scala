package io.travisbrown.incompletes

import argonaut.DecodeJson
import scalaz.Functor

package object demo {
  implicit val decodeJsonFunctor: Functor[DecodeJson] =
    new Functor[DecodeJson] {
      def map[A, B](fa: DecodeJson[A])(f: A => B): DecodeJson[B] = fa.map(f)
    }
}