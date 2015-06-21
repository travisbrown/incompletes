# Incompletes

This project demonstrates an implementation of [Shapeless](https://github.com/milessabin/shapeless)-powered automatic
derivation for "incomplete" type class instances. For example, if you start a
REPL in the demo project with `sbt demo/console`, you can run the following:

```scala
import shapeless._, labelled.{ FieldType, field }
import argonaut._, Argonaut._
import io.travisbrown.incompletes.demo._, ArgonautDerivation._

case class User(id: Long, age: Long, name: String, email: String)

val noId = """
  {
    "age": 25,
    "name": "Foo McBar",
    "email": "foo@mcbar.com"
  }
"""

val noIdOrName = """
  {
    "age": 26,
    "email": "foo@mcbar.com"
  }
"""

val noAge = """
  {
    "id": 1001,
    "name": "Foo McBar",
    "email": "foo@mcbar.com"
  }
"""

val withId = Parse.decodeOption[Long => User](noId).map(_(1001))

val withIdAndName =
  Parse.decodeOption[(Long, String) => User](noIdOrName).map(_(1002, "Foo"))

val withAge =
  Parse.decodeOption[FieldType[Witness.`'age`.T, Long] => User](noAge).map(
    _(field(27))
  )
```

See my blog post [here](https://meta.plasm.us/posts/2015/06/21/deriving-incomplete-type-class-instances/)
for more discussion of the approach.

