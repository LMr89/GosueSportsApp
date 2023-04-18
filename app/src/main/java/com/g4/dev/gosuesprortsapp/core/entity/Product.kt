package com.g4.dev.gosuesprortsapp.core.entity

data class Product(
    val estado: Boolean,
    val idCategoria: Category,
    val idProducto: Int,
    val idProveedor: Proveedor,
    val imgUrl: String,
    val nombre: String,
    val precioUnitario: Double,
    val stock: Int
)