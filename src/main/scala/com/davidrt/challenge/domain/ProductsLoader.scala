package com.davidrt.challenge.domain

import pureconfig._
import pureconfig.generic.auto._

case class Item(code: String, name: String, price: Double)

case class AvailableProducts(availableProducts: List[Item])

object ProductsLoader {
  implicit val products: AvailableProducts = ConfigSource.default.loadOrThrow[AvailableProducts]
}



