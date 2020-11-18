// scala 2.13.3

import $ivy.`com.chuusai::shapeless:2.3.3`
import $ivy.`org.typelevel::cats-core:2.2.0`

import shapeless._
import scala.{:: => :::}
import java.time._
import java.time.format._
import cats.implicits._

import $file.csv_decoder
import csv_decoder._

// TODO: Implement instances for generic `CsvDecoder` derivation
