package com.g4.dev.gosuesprortsapp.data.model.response.common

data class CommonResponseServer(
    val httpStatus: Int,
    val mensaje: String,
    val tiempo: String
)