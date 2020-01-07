package com.davidrt.challenge.domain

import pureconfig._
import pureconfig.generic.auto._

object PromotionsLoader {
  implicit val discounts: AvailableDiscounts = ConfigSource.default.loadOrThrow[AvailableDiscounts]
}

sealed trait Discount {
  def doDiscount(quantity: Int, regularPrice: Double): Double

  def target: String
}

case class BuyMorePayLess(productCode: String, salePrice: Double, minimumUnits: Int) extends Discount {
  override def doDiscount(quantity: Int, regularPrice: Double): Double = {
    if (quantity < minimumUnits) 0
    else (regularPrice - salePrice) * quantity
  }

  override def target: String = productCode
}

case class BuyNPayX(productCode: String, soldUnits: Double, chargedUnits: Double) extends Discount {
  final override def doDiscount(quantity: Int, regularPrice: Double): Double = {
    if (quantity < chargedUnits) 0
    else {
      Math.floor(quantity * (1 - chargedUnits / soldUnits)) * regularPrice //(1-ratio) because we want the discount price
    }
  }

  override def target: String = productCode
}

case class AvailableDiscounts(availableDiscounts: List[Discount])
