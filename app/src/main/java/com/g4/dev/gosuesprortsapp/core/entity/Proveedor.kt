package com.g4.dev.gosuesprortsapp.core.entity

data class Proveedor(
    val direccion: String,
    val estado: Boolean,
    val idProveedor: Int,
    val nomContacto: String,
    val nombre: String,
    val paginaWeb: String,
    val region: String,
    val telefono: String
)