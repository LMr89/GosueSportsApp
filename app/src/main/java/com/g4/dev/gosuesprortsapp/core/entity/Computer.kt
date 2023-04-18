package com.g4.dev.gosuesprortsapp.core.entity

data class Computer(
    val idOrdenador: Int,
    val nomDispositivo: String,
    val caracteristicas: String,
    val ipOrdenador: String,
    val direccionMac: String,
    val numOrdenador: Int,
    val mantenimiento: Boolean,
    val estado: Boolean


)