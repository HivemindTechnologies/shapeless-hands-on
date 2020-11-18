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

val dateFormatter: DateTimeFormatter                 = DateTimeFormatter.ofPattern("yyyy/MM/dd")
implicit val stringDecoder: CsvDecoder[String]       = _.headOption
implicit val doubleDecoder: CsvDecoder[Double]       = _.headOption.flatMap(_.toDoubleOption)
implicit val instantDecoder: CsvDecoder[Instant]     = _.headOption.flatMap(str => Either.catchNonFatal(Instant.parse(str)).toOption)
implicit val localDateDecoder: CsvDecoder[LocalDate] = _.headOption.flatMap(str => Either.catchNonFatal(LocalDate.parse(str, dateFormatter)).toOption)

implicit val hNilDecoder: CsvDecoder[HNil] = _ => Some(HNil)

implicit def hListDecoder[H, T <: HList](implicit hDecoder: CsvDecoder[H], tDecoder: CsvDecoder[T]): CsvDecoder[H :: T] = _ match {
  case Nil     => None
  case h ::: t => (hDecoder.decode(List(h)), tDecoder.decode(t)).mapN(_ :: _)
}

implicit def genericDecoder[A, Repr](implicit gen: Generic.Aux[A, Repr], decoder: CsvDecoder[Repr]): CsvDecoder[A] = decoder.decode(_).map(gen.from)
