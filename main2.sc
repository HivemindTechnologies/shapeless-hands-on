// scala 2.13.3

import $ivy.`com.chuusai::shapeless:2.3.3`

import shapeless._
import java.time._

import $file.models
import models._

/// GENERIC REPRESENTATION WITH SHAPELESS HLIST

val orderAsHList: String :: String :: String :: Instant :: String :: Double :: Double :: Double :: Double :: String :: HNil =
  "1COc2" :: "8HHY1" :: "Market Price" :: Instant.parse("2020-07-16T00:00:00.206502171Z") :: "Quote" :: 28.8 :: 13.0 :: 28.81 :: 2.0 :: "TTZ[MKT_ST_IND]" :: HNil

println(orderAsHList.head)
println(orderAsHList.tail)

/// SWITCHING REPRESENTATIONS

val order = Order(
  "1COc2",
  "8HHY1",
  "Market Price",
  Instant.parse("2020-07-16T00:00:00.206502171Z"),
  "Quote",
  28.8,
  13.0,
  28.81,
  2.0,
  "TTZ[MKT_ST_IND]"
)

val orderGenericRepresentation: String :: String :: String :: Instant :: String :: Double :: Double :: Double :: Double :: String :: HNil =
  Generic[Order].to(order)

println(orderGenericRepresentation)

val concreteOrder: Order = Generic[Order].from(orderGenericRepresentation)

println(concreteOrder)

assert(order == concreteOrder)