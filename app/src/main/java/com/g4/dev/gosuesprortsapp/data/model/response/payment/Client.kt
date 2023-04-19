package com.g4.dev.gosuesprortsapp.data.model.response.payment

data class Client(
    val device_fingerprint: Any,
    val ip_country_code: String,
    val ip: String,
    val browser: String,
    val ip_country: String,
    val device_type: String
)