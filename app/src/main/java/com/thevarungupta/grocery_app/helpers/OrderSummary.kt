package com.thevarungupta.grocery_app.helpers

data class OrderSummary(
    var totalMrp: Int? = null,
    var totalPrice: Int? = null,
    var discount: Int? = null,
    var deliveryCharges: Int? = null,
    var orderAmount: Int? = null
)