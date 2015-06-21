package io.travisbrown.incompletes

import scalaz.Functor
import shapeless._
import shapeless.ops.function.FnFromProduct

/**
 * Provides incomplete instances for some type class `C`.
 *
 * @author Travis Brown
 */
trait Incompletes[C[_]] {
  implicit def incompleteInstance[
    F,          // The `FunctionN` that we want an instance for.
    P <: HList, // The patch (possibly unlabeled).
    A,          // The case class (or whatever) that we're targeting.
    T <: HList, // The labeled representation of `A`.
    R <: HList  // The remaining fields.
  ](implicit
    ffp: FnFromProduct.Aux[P => A, F],
    gen: LabelledGeneric.Aux[A, T],
    complement: Complement.Aux[T, P, R],
    functor: Functor[C],
    instance: C[R]
  ): C[F] = functor.map(instance)(r =>
    ffp(p => gen.from(complement.insert(p, r)))
  )
}
