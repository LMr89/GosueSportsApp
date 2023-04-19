package com.g4.dev.gosuesprortsapp.data.model.response.payment

data class MethodsPaymentTokenResponse(
    val metadata: Metadata,
    val card_number: String,
    val last_four: String,
    val active: Boolean,
    val client: Client,
    val id: String,
    val creation_date: Long,
    val type: String,
    val email: String,
    val `object`: String,
    val iin: Iin
)