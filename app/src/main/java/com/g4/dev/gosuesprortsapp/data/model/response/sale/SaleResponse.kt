package com.g4.dev.gosuesprortsapp.data.model.response.sale

data class SaleResponse(
    val idVenta: Int,
    val clienteNombre: String,
    val fecha: String,
    val igv: Double,
    val total: Double,
    val totalItems: Int,
    val ticketUri: String
)