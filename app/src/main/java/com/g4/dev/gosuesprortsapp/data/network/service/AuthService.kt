package com.g4.dev.gosuesprortsapp.data.network.service

import com.g4.dev.gosuesprortsapp.core.retrofit.RetrofitHelper
import com.g4.dev.gosuesprortsapp.data.model.request.AuthLoginRequest
import com.g4.dev.gosuesprortsapp.data.model.response.AuthResponse
import com.g4.dev.gosuesprortsapp.data.network.interfaces.AuthApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthService {
    private val userRetrofit = RetrofitHelper.getRetrofit()

    suspend fun  authenticateUser(login:AuthLoginRequest):AuthResponse?{
        return  withContext(Dispatchers.IO){
            val response = userRetrofit
                .create(AuthApiClient::class.java)
                .postLogin(login)
            response.body()
        }
    }
}