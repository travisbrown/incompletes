package io.travisbrown.incompletes
package demo

import argonaut.{ AutoDecodeJsons, AutoEncodeJsons, DecodeJson }

object ArgonautDerivation
  extends AutoDecodeJsons with AutoEncodeJsons
  with Incompletes[DecodeJson]
