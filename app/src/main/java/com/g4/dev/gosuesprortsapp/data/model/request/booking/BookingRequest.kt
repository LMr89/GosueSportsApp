package com.g4.dev.gosuesprortsapp.data.model.request.booking

import com.g4.dev.gosuesprortsapp.data.model.request.sale.IdCliente
import com.g4.dev.gosuesprortsapp.data.model.request.sale.IdUsuario

data class BookingRequest(
    val fechaInicio: String,
    val tiempo: Int,
    val idOrdenador: IdOrdenador?,
    val cliente: IdCliente?,
    val idUsuario: IdUsuario?,
    val monto: Double
){
    constructor() : this("",0, null, null, null, 0.0)
}