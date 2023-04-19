package com.g4.dev.gosuesprortsapp.data.model.response.payment

data class Iin(
    val installments_allowed: List<Any>,
    val bin: String,
    val card_category: Any,
    val card_brand: String,
    val card_type: String,
    val issuer: Issuer,
    val `object`: String
)