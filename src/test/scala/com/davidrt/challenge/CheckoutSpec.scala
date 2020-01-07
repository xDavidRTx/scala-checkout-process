package com.davidrt.challenge

import com.davidrt.challenge.domain.{Discount, PromotionsLoader}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

class CheckoutSpec extends AnyFlatSpec with Matchers {
  val pricingRules: List[Discount] = PromotionsLoader.discounts.availableDiscounts

  "The price for a empty shopping cart" must " be 0" in {
    val co = Checkout(pricingRules)
    co.total must be(0)
  }

  "If the shopping cart does not contain any products with promotions the price" must "be the sum of the unit prices" in {
    val co = Checkout(pricingRules)
    co.scan("HAT")
    co.scan("HAT")
    co.scan("HAT")
    co.total must be(3 * 7.50)
  }

  //Example 1 from the readme
  "If no promotions are valid the price " must "be the sum of the unit prices" in {
    val co = Checkout(pricingRules)
    co.scan("TICKET")
    co.scan("HOODIE")
    co.scan("HAT")
    co.total must be(32.50)
  }

  //Example 2 from the readme
  "The 2-for-1 promotion for the TICKET" must "be applied" in {
    val co = Checkout(pricingRules)
    co.scan("TICKET")
    co.scan("HOODIE")
    co.scan("TICKET")
    co.total must be(25.00)
  }

  //Example 3 from the readme
  "The buy 3 or more HOODIE promotion" must "be applied" in {
    val co = Checkout(pricingRules)
    co.scan("HOODIE")
    co.scan("HOODIE")
    co.scan("HOODIE")
    co.scan("TICKET")
    co.scan("HOODIE")
    co.total must be(81.00)
  }

  //Example 4 from the readme
  "Even with several items in random order the price" must "be correct" in {
    val co = Checkout(pricingRules)
    co.scan("TICKET")
    co.scan("HOODIE")
    co.scan("TICKET")
    co.scan("TICKET")
    co.scan("HAT")
    co.scan("HOODIE")
    co.scan("HOODIE")
    co.total must be(74.50)
  }
}
