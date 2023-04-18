package com.g4.dev.gosuesprortsapp.data.model.request.sale

data class SaleRequest(
    val idCliente: IdCliente,
    val idUsuario: IdUsuario,
    val detalleVentas: List<DetalleVenta>
)