package com.g4.dev.gosuesprortsapp.data.model.request.payment

data class ChargeRequest(
    val amount: String,
    val currency_code: String,
    val source_id: String,
    val description: String,
    val email: String,
    val installments: Int,
    val metadata: Metadata
)