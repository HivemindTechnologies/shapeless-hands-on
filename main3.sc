// scala 2.13.3

import $file.data
import data._
import $file.models
import models._
import $file.csv_decoder
import csv_decoder._
import $file.generic_instances
import generic_instances._

orderCsv.linesIterator.toList.flatMap(_.split(",").toList.decode[Order]).foreach(println)

tradeCsv.linesIterator.toList.flatMap(_.split(",").toList.decode[Trade]).foreach(println)

productHighCloseCsv.linesIterator.toList.flatMap(_.split(",").toList.decode[ProductHighClose]).foreach(println)
