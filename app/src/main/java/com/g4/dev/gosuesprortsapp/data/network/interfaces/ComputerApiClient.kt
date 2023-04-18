package com.g4.dev.gosuesprortsapp.data.network.interfaces

import com.g4.dev.gosuesprortsapp.core.entity.Computer
import com.g4.dev.gosuesprortsapp.util.RetrofitUrlConstants
import retrofit2.Response
import retrofit2.http.GET

interface ComputerApiClient {

    @GET(RetrofitUrlConstants.GET_COMPUTERS_URL)
    suspend fun  getAllComputers():Response<List<Computer>>
}