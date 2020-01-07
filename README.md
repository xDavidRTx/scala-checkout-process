# Triggerise BE Challenge

Besides providing exceptional rewards for good behaviours, Triggerise also runs a physical store which sells (only) 3 products:

```
Code         | Name                   |  Price
-------------------------------------------------
TICKET       | Triggerise Ticket      |   5.00€
HOODIE       | Triggerise Hoodie      |  20.00€
HAT          | Triggierse Hat         |   7.50€
```

Various departments have insisted on the following discounts:

 * The marketing department believes in 2-for-1 promotions (take two of the same product, one of them is free), and would like for there to be a 2-for-1 special on `TICKET` items.

 * The CFO insists that the best way to increase sales is with discounts on bulk purchases (buying x or more of a product, the price of that product is reduced), and demands that if you buy 3 or more `HOODIE` items, the price per unit should be 19.00€.

Triggerise's checkout process allows for items to be scanned in any order, and should return the total amount to be paid. The interface for the checkout process looks like this (Scala):

```Scala
val co = Checkout(pricing_rules)
co.scan("TICKET")
co.scan("TICKET")
co.scan("HOODIE")
val price = co.total
```

### Configuring the data

Currently the application supports any number of items and any number of promotions of buy-n-pay-x and buy-more-pay-less.
To add/modify the products and discounts just modify the products.conf and promotions.conf files.  

### How to Run the test

`sbt test`