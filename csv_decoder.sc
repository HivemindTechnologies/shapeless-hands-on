// scala 2.13.3

trait CsvDecoder[A] {
  def decode(columns: List[String]): Option[A]
}

implicit class CsvDecoderOps[A](columns: List[String]) {
  def decode[A](implicit decoder: CsvDecoder[A]): Option[A] = decoder.decode(columns)
}
