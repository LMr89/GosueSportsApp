package com.g4.dev.gosuesprortsapp.data.network.interfaces

import com.g4.dev.gosuesprortsapp.core.entity.Usuario
import com.g4.dev.gosuesprortsapp.util.RetrofitUrlConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsuarioApiClient {
    @GET(RetrofitUrlConstants.USER_DATILS_URL)
    suspend fun  getUserDetailsFromUserName(@Query("nomUsuario") username:String) : Response<Usuario>
}