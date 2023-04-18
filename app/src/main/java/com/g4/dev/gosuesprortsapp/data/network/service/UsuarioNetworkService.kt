package com.g4.dev.gosuesprortsapp.data.network.service

import com.g4.dev.gosuesprortsapp.core.entity.Usuario
import com.g4.dev.gosuesprortsapp.core.retrofit.RetrofitHelper
import com.g4.dev.gosuesprortsapp.data.network.interfaces.AuthApiClient
import com.g4.dev.gosuesprortsapp.data.network.interfaces.UsuarioApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsuarioNetworkService {
    private val userRetrofit = RetrofitHelper.getRetrofit()

    suspend fun getUserDetails(username:String):Usuario?{
        return  withContext(Dispatchers.IO){
            val response = userRetrofit
                .create(UsuarioApiClient::class.java)
                .getUserDetailsFromUserName(username)

            response.body()
        }

    }
}