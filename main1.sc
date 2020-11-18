// scala 2.13.3

import $ivy.`org.typelevel::cats-core:2.2.0`

import $file.data
import data._
import $file.models
import models._
import $file.csv_decoder
import csv_decoder._

import cats.implicits._
import java.time._

implicit val orderDecoder: CsvDecoder[Order] = _ match {
  case List(ric, alias, domain, eventTime, orderType, bidPrice, bidSize, askPrice, askSize, qualifiers) =>
    (
      Either.catchNonFatal(Instant.parse(eventTime)).toOption,
      bidPrice.toDoubleOption,
      bidSize.toDoubleOption,
      askPrice.toDoubleOption,
      askSize.toDoubleOption
    ).mapN(Order(ric, alias, domain, _, orderType, _, _, _, _, qualifiers))
  case _ => None
}

val orderLine = List("1COc2", "8HHY1", "Market Price", "2020-07-16T00:00:00.206502171Z", "Quote", "28.8", "13", "28.81", "2", "TTZ[MKT_ST_IND]")

println(orderLine.decode[Order])

orderCsv.linesIterator.toList.flatMap(_.split(",").toList.decode[Order]).foreach(println)
