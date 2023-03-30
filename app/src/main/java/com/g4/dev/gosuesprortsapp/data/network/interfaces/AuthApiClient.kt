package com.g4.dev.gosuesprortsapp.data.network.interfaces

import com.g4.dev.gosuesprortsapp.data.model.request.AuthLoginRequest
import com.g4.dev.gosuesprortsapp.data.model.response.AuthResponse
import com.g4.dev.gosuesprortsapp.util.RetrofitUrlConstants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiClient {

    @POST(RetrofitUrlConstants.AUTH_URL)
    suspend fun  postLogin(@Body login:AuthLoginRequest):Response<AuthResponse>
}