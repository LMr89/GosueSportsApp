package com.g4.dev.gosuesprortsapp.data.network.repository.sale

import com.g4.dev.gosuesprortsapp.core.entity.Product

object SaleTemporal {
    val productsForSale = ArrayList<ProductSale>()
    var currentProductSelected:Product? = null
}