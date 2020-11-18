// scala 2.13.3

import java.time._

final case class Order(
  ric: String,
  alias: String,
  domain: String,
  eventTime: Instant,
  orderType: String,
  bidPrice: Double,
  bidSize: Double,
  askPrice: Double,
  askSize: Double,
  qualifiers: String
)

final case class Trade(
  ric: String,
  alias: String,
  domain: String,
  eventTime: Instant,
  tradeType: String,
  price: Double,
  volume: Double,
  qualifiers: String
)

final case class ProductHighClose(
  ric: String,
  tradeDate: LocalDate,
  high: Double,
  low: Double
)
