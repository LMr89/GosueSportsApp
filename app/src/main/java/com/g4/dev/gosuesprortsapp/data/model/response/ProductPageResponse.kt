package com.g4.dev.gosuesprortsapp.data.model.response

import com.g4.dev.gosuesprortsapp.core.entity.Product

data class ProductPageResponse(
    val content: List<Product>,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageable: Pageable,
    val size: Int,
    val sort: SortX,
    val totalElements: Int,
    val totalPages: Int
)