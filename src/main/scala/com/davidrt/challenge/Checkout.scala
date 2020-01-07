package com.davidrt.challenge

import com.davidrt.challenge.domain.{Discount, Item, ProductsLoader}

import scala.collection.mutable

case class Checkout(pricingRules: List[Discount]) {

  private var shoppingCart: List[Item] = Nil

  //Small hack to be able to transform a code in one full item
  lazy val productsMapper: mutable.HashMap[String, Item] = ProductsLoader.products.availableProducts
    .foldLeft(new mutable.HashMap[String, Item]())((carry, item) => carry.addOne((item.code, item)))

  def scan(code: String): Unit = productsMapper.get(code) match {
    case None =>
      println(s"No product with the code: $code was found")
    case Some(item) =>
      println(s"You added one ${item.name} to the shopping card")
      shoppingCart = shoppingCart :+ item
  }

  def total: Double = {
    val organizedShoppingCart = shoppingCart.groupBy(identity) // First we group the items
      .map { case (item, grouped) => (item, grouped.size) } //then we count them

    val totalDiscounts: Double = pricingRules.map {
      discount: Discount =>
        organizedShoppingCart.find { case (item, _) => item.code.equals(discount.target) } match {
          case None => 0 // If there is no product of this kind no discount is calculated
          case Some((item, quantity)) =>
            println(item, discount.doDiscount(quantity, item.price))
            discount.doDiscount(quantity, item.price)
        }
    }.sum

    organizedShoppingCart.map { case (item, quantity) => item.price * quantity }.sum - totalDiscounts
  }
}
