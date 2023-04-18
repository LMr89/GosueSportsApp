package com.g4.dev.gosuesprortsapp.core.entity

data class Booking(
    val idReserva: Int,
    val fechaInicio: String,
    val fechaFin: String,
    val tiempo: Int,
    val cliente: String,
    val usuario: String,
    val monto: Double,
    val estado: Boolean
)