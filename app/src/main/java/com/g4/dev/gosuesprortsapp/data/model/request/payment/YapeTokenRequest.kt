package com.g4.dev.gosuesprortsapp.data.model.request.payment

data class YapeTokenRequest(
    val otp: String,
    val number_phone: String,
    val amount: String
)