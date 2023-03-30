package com.g4.dev.gosuesprortsapp.data.model.response

import com.g4.dev.gosuesprortsapp.core.entity.Authority

data class AuthResponse(
    var token:String,
    var bearer:String,
    var nomUsuario:String,
    var authorities:List<Authority>

)
