package com.g4.dev.gosuesprortsapp.data.network.repository.sale

import com.g4.dev.gosuesprortsapp.core.entity.Product


data class ProductSale(
    val idProduct: Product,
    var stockChose:Int
    )

