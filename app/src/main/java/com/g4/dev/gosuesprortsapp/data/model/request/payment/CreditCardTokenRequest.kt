package com.g4.dev.gosuesprortsapp.data.model.request.payment

data class CreditCardTokenRequest(
    val card_number: String,
    val cvv: String,
    val expiration_month: Int,
    val expiration_year: String,
    val email: String
)